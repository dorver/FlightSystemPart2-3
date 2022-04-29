package com.FlightSystem.demo.ContactUs.ContactUsService;

import aj.org.objectweb.asm.ModuleVisitor;

import antlr.collections.impl.LList;
import com.FlightSystem.demo.ContactUs.ContactUsDTO.ContactUs;
import com.FlightSystem.demo.ContactUs.ContactUsRepository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsService {

    @Autowired
    ContactUsRepo contactUsRepo;

    public List<ContactUs> getAllContactUs() {
        List<ContactUs> contactUses = new ArrayList<>();
        contactUsRepo.findAll().forEach(contactUses::add);
        return contactUses;
    }

    public ContactUs getContactUs(int id) {
        var res = contactUsRepo.findById(id);
        return res.orElse(null);
    }

    public void addContactUs(ContactUs contactUs){
        contactUsRepo.save(contactUs);

    }

}
