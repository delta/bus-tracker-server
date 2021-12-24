package edu.nitt.delta.bustracker.repository;

import edu.nitt.delta.bustracker.model.Location;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    public Optional<Location> findByDriverId(String driverId);

    @Query(value = "{ $and: [ {'vehicleId': ?0}, {'driverId': ?1} ] }", delete = true)
    public Location deleteLocationByVehicleIdAndDriverId(String vehicleId, String driverId);
}
