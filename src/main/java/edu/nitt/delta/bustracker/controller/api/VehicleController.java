package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.model.Vehicle;
import edu.nitt.delta.bustracker.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

}
