package edu.nitt.delta.bustracker.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
@Builder
public class Location {
    @Id
    private String id;
    
    private String vehicleId;
    private double longitude;
    private double latitude;
    private Date time;

    @Indexed(unique = true)
    private String driverId;
}
