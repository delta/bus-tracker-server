package edu.nitt.delta.bustracker.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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

    public Driver (
        String firstName,
        String lastName,
        String password,
        Integer age,
        String email
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.email = email;
    }

}
