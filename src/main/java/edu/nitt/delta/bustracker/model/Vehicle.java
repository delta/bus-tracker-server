package edu.nitt.delta.bustracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.nitt.delta.bustracker.classes.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Vehicle {

    @Id
    private String id;

    private VehicleType type;
    private Integer seats;

    @Indexed(unique = true)
    private String vehicleNumber;

    public Vehicle(
        VehicleType type, 
        Integer seats,
        String vehicleNumber
    ) {
        this.type = type;
        this.seats = seats;
        this.vehicleNumber = vehicleNumber;
    }

}
