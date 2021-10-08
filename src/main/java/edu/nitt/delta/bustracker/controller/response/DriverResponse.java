package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.Driver;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverResponse {
    private Driver driver;
    private String message;
}
