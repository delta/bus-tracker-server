package edu.nitt.delta.bustracker.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverResponse {    
    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
