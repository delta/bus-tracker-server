package edu.nitt.delta.bustracker.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Document
public class Driver {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String password;
    private String gender;

    private Integer age;

    @Indexed(unique = true)
    private String email;


}
