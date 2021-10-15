package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.controller.response.VehicleListResponse;
import edu.nitt.delta.bustracker.controller.response.VehicleResponse;
import edu.nitt.delta.bustracker.model.Vehicle;
import edu.nitt.delta.bustracker.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<VehicleListResponse> getAllVehicle() {

        try {
            List<Vehicle> data = vehicleService.getAllVehicle();

            VehicleListResponse res = VehicleListResponse
                .builder()
                .vehicles(data)
                .message("OK")
                .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(VehicleListResponse
                .builder()
                .message("Something went wrong.")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable String id) {

        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);

            if (vehicle == null) {
                return new ResponseEntity<>(VehicleResponse
                    .builder()
                    .message("Invalid Id")
                    .build(), 
                    HttpStatus.NOT_FOUND
                );
            }
    
            return new ResponseEntity<>(VehicleResponse
                .builder()
                .vehicle(vehicle)
                .message("OK")
                .build(), 
                HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(VehicleResponse
                .builder()
                .message("Something went wrong.")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
