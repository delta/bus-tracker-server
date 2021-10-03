package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Vehicle;
import edu.nitt.delta.bustracker.repository.VehicleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {

        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isPresent()) {
            return vehicle.get();
        } 
        
        return null;
    }

}
