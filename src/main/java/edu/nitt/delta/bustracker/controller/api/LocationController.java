package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.service.LocationService;


@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired 
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocation() {
        return locationService.getAllLocation();
    } 

}
