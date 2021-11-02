package edu.nitt.delta.bustracker.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String jwt;
    private String message;
}
