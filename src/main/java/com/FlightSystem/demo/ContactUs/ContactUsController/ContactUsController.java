package com.FlightSystem.demo.ContactUs.ContactUsController;

import com.FlightSystem.demo.ContactUs.ContactUsDTO.ContactUs;
import com.FlightSystem.demo.ContactUs.ContactUsService.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactUsController {

    @Autowired
    ContactUsService contactUsService;

    @GetMapping("/contactus")
    public List<ContactUs> getAll(){
        return contactUsService.getAllContactUs();
    }

    @GetMapping("/contactus/{id}")
    public ContactUs get(@PathVariable int id){
        var res= contactUsService.getContactUs(id);
        return (res != null ? res:new ContactUs());
    }

    @PostMapping("/contactus")
    public void add(@RequestBody ContactUs contactUs){
        contactUsService.addContactUs(contactUs);
    }
}

