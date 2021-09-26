package edu.nitt.delta.bustracker.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;


@Data
@Document
@Builder
public class Location {
    @Id
    private String id;

    @Indexed(unique = true)
    private String driverId;
    private String vehicleId;

    private double longitude;
    private double latitude;
    private Date time;


}
