package edu.nitt.delta.bustracker.controller.response;

import java.util.List;

import edu.nitt.delta.bustracker.model.Vehicle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleListResponse {
    private List<Vehicle> vehicles;
    private String message;
}
