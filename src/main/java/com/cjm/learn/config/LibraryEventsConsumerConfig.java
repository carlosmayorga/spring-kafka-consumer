package com.cjm.learn.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
public class LibraryEventsConsumerConfig {

	
	 
	@Bean
	ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configure,
			ConsumerFactory<Object, Object> kafkaConsumerFactory) {

		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		configure.configure(factory, kafkaConsumerFactory);
		
		/* Handle Concurrency : This conf set 3 Threads Containers for handle the partitions,
		 * so, in this case, we have a dedicate Thread to handle each partition.
		 * 
		 * By default Spring assign 1 thread to handle all partitions of the topic.
		 * If we have the microservice in kubernetes isn't necessary, with 1 thread in each jar is sufficient. 
		 */
		factory.setConcurrency(3);

		
		/*
		 * If you need to handle manually the commit message to the producer, uncomment the line below
		 */
		//factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		
		return factory;
	}
	

}
