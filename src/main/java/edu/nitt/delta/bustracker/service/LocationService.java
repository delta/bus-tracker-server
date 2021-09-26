package edu.nitt.delta.bustracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.repository.DriverRepository;
import edu.nitt.delta.bustracker.repository.LocationRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final DriverRepository driverRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;

    public Boolean insertLocation(Location location) {

        try {
            locationRepository.save(location);
            return true;

        } catch (Error e) {
            return false;
        }
        
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public List<Location> getAllActiveDriverId() {
        return locationRepository.findAllActiveDriverId();
    }

    public List<Driver> getAllActiveDrivers() {

        List<Location> Ids = getAllActiveDriverId();
        List<Driver> driverList = new ArrayList<>();

        Ids.forEach(Id -> {
            driverList.add(driverRepository.findById(Id.getDriverId()).get());    
        });

        return driverList;

    }

    public Location getLocationById(String id) {

        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent()) {
            return location.get();
        }
        
        return null;
    
    }

    public Location getLocationByDriverId(String id) {
        Optional<Location> location = locationRepository.findOneBydriverId(id);

        if (location.isPresent()) return location.get();
        return null;
    }

    public void updateLocationByDriverId(String driverId, Location location) {

        Query query = new Query(Criteria.where("driverId").is(driverId));
        Location val = mongoTemplate.findOne(query, Location.class);
        
        val.setLatitude(location.getLatitude());
        val.setLongitude(location.getLongitude());
        val.setTime(location.getTime());

        mongoTemplate.save(val);

    }

    public void updateLocationById(String id, Location location) {

        Query query = new Query(Criteria.where("_id").is(id));
        Location val = mongoTemplate.findOne(query, Location.class);

        val.setLatitude(location.getLatitude());
        val.setLongitude(location.getLongitude());
        val.setTime(location.getTime());

        mongoTemplate.save(val);

    }
    
    public void deleteLocationById(String id) {
        locationRepository.deleteById(id);
    }

}
