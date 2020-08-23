package com.cjm.learn.repository;

import org.springframework.data.repository.CrudRepository;

import com.cjm.learn.entity.LibraryEvent;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent, Integer> {
	
	

}
