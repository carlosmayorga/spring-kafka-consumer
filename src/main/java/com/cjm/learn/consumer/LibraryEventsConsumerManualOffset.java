package com.cjm.learn.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;


/*
 * This class is for make the ack manualy.
 * This is the way to advice to producer, that one consumer has took the message.
 * 
 * If any consumer advice to producer, the producer will persist the message until one consumer commit the message. 
 */

// @Component
public class LibraryEventsConsumerManualOffset implements AcknowledgingMessageListener<Integer, String> {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@KafkaListener(topics = {"library-events"})
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
		
		log.info("ConsumerRecordManualOffset : {} ", consumerRecord);
		acknowledgment.acknowledge();
		
	}

}
