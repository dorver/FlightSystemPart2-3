package com.FlightSystem.demo.ContactUs.ContactUsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.print.DocFlavor;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //Data Transfer Object
public class ContactUs {

    @Id
    private int messageId;
    private String senderName;
    private String content;

}
