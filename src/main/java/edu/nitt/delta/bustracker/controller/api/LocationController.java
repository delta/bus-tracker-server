package edu.nitt.delta.bustracker.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.service.LocationService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired 
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocation() {
        return locationService.getAllLocation();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

    @GetMapping("/driver")
    public List<Driver> getAllActiveDrivers() {
        return locationService.getAllActiveDrivers();
    }

    @GetMapping("/driver/{id}")
    public Location getLocationByDriverId(@PathVariable String driverId) {
        return locationService.getLocationByDriverId(driverId);
    }

    @GetMapping("/insert")
    public boolean insertLocation(@RequestBody Location location) {

        if (location.getTime() == null) {
            location.setTime(new Date());
        }
        // System.out.println(location);
        // return true;
        return locationService.insertLocation(location);
    }

    @GetMapping("/update/driver/{driverId}")
    public boolean updateLocationByDriverId(@PathVariable String driverId, @RequestBody Location location) {

        if (locationService.getLocationByDriverId(driverId) != null){

            if (location.getTime() == null) {
                location.setTime(new Date());
            }

            locationService.updateLocationByDriverId(driverId, location);
            return true;

        }

        return false;

    }
    
    @GetMapping("/update/{id}")
    public boolean updateLocationById(@PathVariable String id,@RequestBody Location location) {


        if (locationService.getLocationById(id) != null) {

            if (location.getTime() == null) {
                location.setTime(new Date());
            }

            locationService.updateLocationById(id, location);
            return true;

        }

        return false;
    }

    @GetMapping("/delete/{id}")
    public void deleteLocationById(@PathVariable String id) {
        locationService.deleteLocationById(id);
    }

}
