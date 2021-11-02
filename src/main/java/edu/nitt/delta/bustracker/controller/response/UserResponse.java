package edu.nitt.delta.bustracker.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private DriverResponse driver;
    private String message;
}
