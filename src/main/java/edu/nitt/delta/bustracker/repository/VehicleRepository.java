package edu.nitt.delta.bustracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nitt.delta.bustracker.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    
}
