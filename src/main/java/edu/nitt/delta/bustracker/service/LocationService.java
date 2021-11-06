package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.repository.LocationRepository;
import edu.nitt.delta.bustracker.repository.UserRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public List<String> getAllVehicleId() {
        List<String> vehicleIds = new ArrayList<>();
        
        getAllLocation().forEach(location -> {
            vehicleIds.add(location.getVehicleId());
        });

        return vehicleIds;
    }

    public Location updateLocation(Location location, String mobileNumber) {
        String driverId = userRepository.findByMobileNumber(mobileNumber).get().getId();

        Query query = new Query().addCriteria(
            new Criteria().andOperator(
                Criteria.where("vehicleId").is(location.getVehicleId()),
                Criteria.where("driverId").is(driverId)
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

    public Boolean deleteLocation(Location location, String mobileNumber) {
        String driverId = userRepository.findByMobileNumber(mobileNumber).get().getId();
        Location deletedLocation = locationRepository.deleteLocationByVehicleIdAndDriverId(location.getVehicleId(), driverId);
        return deletedLocation != null;
    }
}
