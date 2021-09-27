package edu.nitt.delta.bustracker.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document
public class Driver {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String password;

    @Indexed(unique = true)
    private String mobileNumber;
}
