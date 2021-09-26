package edu.nitt.delta.bustracker.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Document
public class Location {
    @Id
    private String id;

    @Indexed(unique = true)
    private String driverId;
    private String vehicleId;

    private double longitude;
    private double latitude;
    private Date time;

    public Location(
        String driverId,
        String vehicleId,
        double longitude,
        double latitude,
        Date time
    ) {
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

}
