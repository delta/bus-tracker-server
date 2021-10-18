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
    private DriverRepository driverRepository;

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(String id) {

        Optional<Driver> driver = driverRepository.findById(id);

        return driver.orElse(null);
    }

    public Driver insertDriver(Driver driver) {

        return driverRepository.save(driver);

    }


}
