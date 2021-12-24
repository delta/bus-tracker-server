package edu.nitt.delta.bustracker.service;

import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.model.VehicleType;
import edu.nitt.delta.bustracker.repository.LocationRepository;
import edu.nitt.delta.bustracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired private LocationRepository locationRepository;

    @Autowired private MongoTemplate mongoTemplate;

    @Autowired private UserRepository userRepository;

    @Autowired private VehicleService vehicleService;

    public Optional<Location> getLocationByDriverId(String driverId) {
        return locationRepository.findByDriverId(driverId);
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public List<String> getAllVehicleId() {
        List<String> vehicleIds = new ArrayList<>();

        getAllLocation()
                .forEach(
                        location -> {
                            vehicleIds.add(location.getVehicleId());
                        });

        return vehicleIds;
    }

    public Location updateLocation(Location location, String mobileNumber) {
        String driverId = userRepository.findByMobileNumber(mobileNumber).get().getId();

        Query query =
                new Query()
                        .addCriteria(
                                new Criteria()
                                        .andOperator(
                                                Criteria.where("vehicleId")
                                                        .is(location.getVehicleId()),
                                                Criteria.where("driverId").is(driverId)));

        Location foundLocation = mongoTemplate.findOne(query, Location.class);

        if (foundLocation == null) {
            VehicleType vehicleType = vehicleService.getVehicleType(location.getVehicleId());
            location.setIsOccupied(null);
            if (vehicleType == VehicleType.ERICKSHAW) {
                location.setIsOccupied(false);
            }

            foundLocation =
                    Location.builder()
                            .driverId(location.getDriverId())
                            .vehicleId(location.getVehicleId())
                            .isOccupied(location.getIsOccupied())
                            .build();
        }

        foundLocation.setLatitude(location.getLatitude());
        foundLocation.setLongitude(location.getLongitude());
        foundLocation.setTime(new Date());

        return mongoTemplate.save(foundLocation);
    }

    public Boolean deleteLocation(Location location, String mobileNumber) {
        String driverId = userRepository.findByMobileNumber(mobileNumber).get().getId();
        Location deletedLocation =
                locationRepository.deleteLocationByVehicleIdAndDriverId(
                        location.getVehicleId(), driverId);
        return deletedLocation != null;
    }

    public Boolean toggleStatus(String driverId) {
        Optional<Location> location = this.getLocationByDriverId(driverId);
        if (location.isEmpty() || location.get().getIsOccupied() == null) return null;
        Location locationDoc = location.get();
        locationDoc.setIsOccupied(!locationDoc.getIsOccupied());
        locationRepository.save(locationDoc);
        return locationDoc.getIsOccupied();
    }
}
