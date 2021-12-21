package edu.nitt.delta.bustracker.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserListResponse {
    private List<DriverResponse> drivers;
    private String message;
}
