package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Location updateLocation(Location location) {

        Query query = new Query().addCriteria(
            new Criteria().andOperator(
                Criteria.where("vehicleId").is(location.getVehicleId()),
                Criteria.where("driverId").is(location.getDriverId())
            )
        );

        Location foundLocation = mongoTemplate.findOne(query, Location.class);

        if (foundLocation == null) {

            foundLocation = Location.builder()
                .driverId(location.getDriverId())
                .vehicleId(location.getVehicleId())
                .build();
        }

        foundLocation.setLatitude(location.getLatitude());
        foundLocation.setLongitude(location.getLongitude());
        foundLocation.setTime(new Date());

        return mongoTemplate.save(foundLocation);
    }

    public Boolean deleteLocation(Location location) {
        Location deletedLocation = locationRepository.deleteLocationByVehicleIdAndDriverId(location.getVehicleId(), location.getDriverId());
        return deletedLocation != null;
    }
}
