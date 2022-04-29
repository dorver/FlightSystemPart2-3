package com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingDTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document("AddRequestsDocumenting")
public class AddRequestsDocumenting {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @Field("time")
    private LocalDateTime time;




}
