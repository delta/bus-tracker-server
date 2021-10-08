package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.controller.response.LocationResponse;
import edu.nitt.delta.bustracker.model.Location;
import edu.nitt.delta.bustracker.service.LocationService;


@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired 
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<LocationResponse> getAllLocation() {
        try {
            List<Location> data = locationService.getAllLocation();

            LocationResponse res = LocationResponse
                .builder()
                .locations(data)
                .message("OK")
                .build();

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(LocationResponse
                .builder()
                .message("Something went wrong.")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    } 

}
