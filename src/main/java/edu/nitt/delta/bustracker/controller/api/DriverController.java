package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired 
    private DriverService driverService;

    @GetMapping
    public List<Driver> getAllDriver() {
        return driverService.getAllDriver();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable String id) {

        Driver driver = driverService.getDriverById(id);

        if (driver == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(driver, HttpStatus.OK);

    }

}
