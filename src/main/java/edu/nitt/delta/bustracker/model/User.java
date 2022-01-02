package edu.nitt.delta.bustracker.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class User {
    @Id private String id;

    private String firstName;
    private String lastName;
    private String password;
    private Role role;

    @Indexed(unique = true)
    private String mobileNumber;
}
