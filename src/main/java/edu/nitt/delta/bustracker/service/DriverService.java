package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.repository.DriverRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(String id) {

        Optional<Driver> driver = driverRepository.findById(id);

        if (driver.isPresent()) {
            return driver.get();
        }

        return null;
    }

    public String insertDriver(Driver driver) {

        if (!driverRepository.findAllByemail(driver.getEmail()).isEmpty()) {
            return "Driver already exists";
        }

        driverRepository.save(driver);
        return "Inserted";

    }

}
