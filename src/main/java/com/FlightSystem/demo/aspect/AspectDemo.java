package com.FlightSystem.demo.aspect;

import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingDTO.AddRequestsDocumenting;
import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingRepository.AddRequestsDocumentingRepo;
import com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumatingService.SequenceGeneratorService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class AspectDemo {

    @Autowired
    AddRequestsDocumentingRepo addRequestsDocumentingRepo;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    //Adds a new document in mongoDB before every add request
    @Before("execution(* com.FlightSystem.demo.DAO.*.add*(..))")
    public void beforeAdding(){
        AddRequestsDocumenting addRequestsDocumenting = new AddRequestsDocumenting();
        addRequestsDocumenting.setId(sequenceGeneratorService.generateSequence(AddRequestsDocumenting.SEQUENCE_NAME));
        addRequestsDocumenting.setTime( LocalDateTime.now());
        addRequestsDocumentingRepo.save(addRequestsDocumenting);
        System.out.println("aspect before adding");
    }
}
