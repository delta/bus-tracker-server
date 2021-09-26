package edu.nitt.delta.bustracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document
@Builder
public class Vehicle {

    @Id
    private String id;

    private VehicleType type;
    private Integer seats;

    @Indexed(unique = true)
    private String vehicleNumber;


}
