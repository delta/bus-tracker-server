package edu.nitt.delta.bustracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nitt.delta.bustracker.model.Location;

public interface LocationRepository extends MongoRepository<Location, String> {

    @Query(value = "{ $and: [ {'vehicleId': ?0}, {'driverId': ?1} ] }", delete = true)
    public Location deleteLocationByVehicleIdAndDriverId(String vehicleId, String driverId);

}
