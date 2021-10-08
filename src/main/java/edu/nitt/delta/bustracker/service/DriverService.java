package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired 
    DriverRepository driverRepository;

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

}
