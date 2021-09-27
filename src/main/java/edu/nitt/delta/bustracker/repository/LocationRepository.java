package edu.nitt.delta.bustracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nitt.delta.bustracker.model.Location;

public interface LocationRepository extends MongoRepository<Location, String> {

}
