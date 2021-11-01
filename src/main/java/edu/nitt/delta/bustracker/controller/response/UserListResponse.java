package edu.nitt.delta.bustracker.controller.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListResponse {
    private List<DriverResponse> drivers;
    private String message;
}
