package edu.nitt.delta.bustracker.repository;

import edu.nitt.delta.bustracker.model.Vehicle;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {}
