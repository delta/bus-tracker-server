package edu.nitt.delta.bustracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.repository.LocationRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

}
