package edu.nitt.delta.bustracker.service;

import edu.nitt.delta.bustracker.model.Vehicle;
import edu.nitt.delta.bustracker.model.VehicleType;
import edu.nitt.delta.bustracker.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired private VehicleRepository vehicleRepository;

    @Autowired private MongoTemplate mongoTemplate;

    @Autowired private LocationService locationService;

    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.orElse(null);
    }

    public Vehicle insertVehicle(Vehicle vehicle) {
        return vehicleRepository.insert(vehicle);
    }

    public List<Vehicle> getAllActiveVehicles(VehicleType vehicleType) {
        List<String> vehicleIds = locationService.getAllVehicleId();
        Query query = new Query().addCriteria(Criteria.where("id").in(vehicleIds));
        if (vehicleType != null) {
            query.addCriteria(Criteria.where("type").is(vehicleType));
        }
        return mongoTemplate.find(query, Vehicle.class);
    }

    public List<Vehicle> getAllInactiveVehicles(VehicleType vehicleType) {
        List<String> vehicleIds = locationService.getAllVehicleId();
        Query query = new Query().addCriteria(Criteria.where("id").nin(vehicleIds));
        if (vehicleType != null) {
            query.addCriteria(Criteria.where("type").is(vehicleType));
        }
        return mongoTemplate.find(query, Vehicle.class);
    }

    public VehicleType getVehicleType(String id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null) {
            return vehicle.getType();
        }
        return null;
    }
}
