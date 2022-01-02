package edu.nitt.delta.bustracker.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Document
public class Location {
    @Id private String id;

    private String vehicleId;
    private double longitude;
    private double latitude;
    private Boolean isOccupied;

    @DateTimeFormat
    @Indexed(expireAfterSeconds = 5 * 60)
    private Date time;

    @Indexed(unique = true)
    private String driverId;
}
