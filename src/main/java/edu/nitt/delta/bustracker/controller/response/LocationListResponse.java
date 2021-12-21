package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.Location;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LocationListResponse {
    private List<Location> locations;
    private String message;
}
