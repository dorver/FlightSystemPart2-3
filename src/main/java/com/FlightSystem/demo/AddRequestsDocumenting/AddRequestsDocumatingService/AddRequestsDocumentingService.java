package com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumatingService;

import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingDTO.AddRequestsDocumenting;
import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingRepository.AddRequestsDocumentingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddRequestsDocumentingService {

    @Autowired
    AddRequestsDocumentingRepo addRequestsDocumentingRepo;

    public void addDoc(AddRequestsDocumenting addRequestsDocumenting) {
        addRequestsDocumentingRepo.save(addRequestsDocumenting);
    }

    public AddRequestsDocumenting findById(long id){
        return addRequestsDocumentingRepo.findById((int) id).orElse(null);
    }
}
