package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.service.DriverService;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired 
    private DriverService driverService;

    @GetMapping
    public List<Driver> getAllDriver() {
        return driverService.getAllDriver();
    }

    @GetMapping("/{id}")
    public Driver getDriver(@PathVariable String id) {
        return driverService.getDriverById(id);
    }

}
