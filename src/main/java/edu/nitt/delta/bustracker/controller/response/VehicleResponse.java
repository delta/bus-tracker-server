package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.Vehicle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Vehicle vehicle;
    private String message;
}
