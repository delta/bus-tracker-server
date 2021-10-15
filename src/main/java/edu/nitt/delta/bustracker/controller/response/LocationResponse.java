package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {
    private Location location;
    private String message;
}
