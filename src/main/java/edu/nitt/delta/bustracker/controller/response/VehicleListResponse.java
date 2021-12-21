package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.Vehicle;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VehicleListResponse {
    private List<Vehicle> vehicles;
    private String message;
}
