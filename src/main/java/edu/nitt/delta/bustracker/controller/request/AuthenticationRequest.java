package edu.nitt.delta.bustracker.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
    private String mobileNumber;
    private String password;
}
