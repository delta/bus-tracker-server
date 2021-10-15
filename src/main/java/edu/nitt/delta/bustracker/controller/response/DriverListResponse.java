package edu.nitt.delta.bustracker.controller.response;

import java.util.List;

import edu.nitt.delta.bustracker.model.Driver;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverListResponse {
    private List<Driver> drivers;
    private String message;
}
