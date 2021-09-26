package edu.nitt.delta.bustracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nitt.delta.bustracker.model.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
    

    @Query(value = "{}", fields = "{'id': 1, 'driverId': 1}")
    public List<Location> findAllActiveDriverId();

    public Optional<Location> findOneBydriverId(String driverId);

}
