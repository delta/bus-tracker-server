package edu.nitt.delta.bustracker.controller.response;

import java.util.List;

import edu.nitt.delta.bustracker.model.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationListResponse {
    private List<Location> locations;
    private String message;
}
