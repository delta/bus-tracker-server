package edu.nitt.delta.bustracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nitt.delta.bustracker.model.Driver;

public interface DriverRepository extends MongoRepository<Driver, String> {

    @Query(value = "{'email': ?0}")
    List<Driver> findAllByemail(String email);

}
