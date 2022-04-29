package com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingRepository;

import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingDTO.AddRequestsDocumenting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddRequestsDocumentingRepo extends MongoRepository<AddRequestsDocumenting, Integer> {
}
