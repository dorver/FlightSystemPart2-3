package com.FlightSystem.demo.ContactUs.ContactUsRepository;
import com.FlightSystem.demo.ContactUs.ContactUsDTO.ContactUs;
import org.springframework.data.repository.CrudRepository;

public interface ContactUsRepo extends CrudRepository<ContactUs,Integer> {
    // get all
    // get id
    // update
    // delete
}