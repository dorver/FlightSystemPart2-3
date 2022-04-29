package com.FlightSystem.demo.AddRequestsDocumenting.AddRequestsDocumentingDTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document("DbSequences")
public class DbSequence {

    @Id
    private String id;

    private long seq;
}
