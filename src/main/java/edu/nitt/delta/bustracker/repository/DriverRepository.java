package edu.nitt.delta.bustracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nitt.delta.bustracker.model.Driver;

public interface DriverRepository extends MongoRepository<Driver, String> {

}
