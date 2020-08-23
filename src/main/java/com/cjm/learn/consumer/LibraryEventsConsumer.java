package com.cjm.learn.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cjm.learn.service.LibraryEventsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Component
public class LibraryEventsConsumer {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LibraryEventsService libraryEventsService;
	
	@KafkaListener(topics = {"library-events"})
	public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		
		log.info("ConsumerRecord : {} ", consumerRecord);
		
		libraryEventsService.processLibraryEvent(consumerRecord);
		
	}

}
