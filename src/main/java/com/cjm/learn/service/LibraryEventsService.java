package com.cjm.learn.service;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjm.learn.entity.LibraryEvent;
import com.cjm.learn.repository.LibraryEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LibraryEventsService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private LibraryEventsRepository libraryEventsRepository;

	public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord)
			throws JsonMappingException, JsonProcessingException {

		LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);

		log.info("LibraryEvent: {}", libraryEvent);

		switch (libraryEvent.getLibraryEventType()) {
		case NEW:
			save(libraryEvent);

			break;

		case UPDATE:
			validate(libraryEvent);
			save(libraryEvent);
			break;

		default:
			log.error("Invalid Library Event Type");
			break;
		}

	}

	private void validate(LibraryEvent libraryEvent) {

		if (libraryEvent.getLibraryEventId() == null) {
			throw new IllegalArgumentException("Library Event Id is missing");
		}

		Optional<LibraryEvent> libraryEventOptional = libraryEventsRepository
				.findById(libraryEvent.getLibraryEventId());

		if (!libraryEventOptional.isPresent()) {
			throw new IllegalArgumentException("Library Event Id is missing");
		}
		log.info("Validation is success");
		
	}

	private void save(LibraryEvent libraryEvent) {
		// TODO Auto-generated method stub
		libraryEvent.getBook().setLibraryEvent(libraryEvent);
		libraryEventsRepository.save(libraryEvent);

		log.info("Successfully Persisted the library event : {}", libraryEvent);
	}

}
