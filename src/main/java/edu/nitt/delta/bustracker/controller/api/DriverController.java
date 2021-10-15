package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.controller.response.DriverListResponse;
import edu.nitt.delta.bustracker.controller.response.DriverResponse;
import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired 
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<DriverListResponse> getAllDriver() {

        try {
            List<Driver> data = driverService.getAllDriver();

            DriverListResponse res = DriverListResponse
                .builder()
                .drivers(data)
                .message("OK")
                .build();

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(DriverListResponse
                .builder()
                .message("Something went wrong.")
                .build(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable String id) {

        try {
            Driver driver = driverService.getDriverById(id);

            if (driver == null) {
                return new ResponseEntity<>(DriverResponse
                    .builder()
                    .message("Invalid Id")
                    .build(), 
                    HttpStatus.NOT_FOUND
                );
            }
    
            return new ResponseEntity<>(DriverResponse
                .builder()
                .driver(driver)
                .message("OK")
                .build(), 
                HttpStatus.OK
            );
            
        } catch (Exception e) {
            return new ResponseEntity<>(DriverResponse
                .builder()
                .message("Something went wrong.")
                .build(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
