package edu.nitt.delta.bustracker.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.controller.response.UserListResponse;
import edu.nitt.delta.bustracker.controller.response.UserResponse;
import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.model.User;
import edu.nitt.delta.bustracker.service.UserService;

@RestController
@RequestMapping("/driver")
public class UserController {

    @Autowired 
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserListResponse> getAllDriver() {

        try {
            List<User> data = userService.getAllDriver();

            UserListResponse res = UserListResponse
                .builder()
                .users(data)
                .message("OK")
                .build();

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(UserListResponse
                .builder()
                .message("Something went wrong.")
                .build(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getDriver(@PathVariable String id) {

        try {
            User user = userService.getDriverById(id);

            if (user == null) {
                return new ResponseEntity<>(UserResponse
                    .builder()
                    .message("Invalid Id")
                    .build(), 
                    HttpStatus.NOT_FOUND
                );
            }
    
            return new ResponseEntity<>(UserResponse
                .builder()
                .user(user)
                .message("OK")
                .build(), 
                HttpStatus.OK
            );
            
        } catch (Exception e) {
            return new ResponseEntity<>(UserResponse
                .builder()
                .message("Something went wrong.")
                .build(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping
    public ResponseEntity<UserResponse> insertDriver(@RequestBody User driver) {

        try {
            driver.setRole(Role.DRIVER);
            User insertedUser = userService.insertUser(driver);

            UserResponse res = UserResponse
                .builder()
                .user(insertedUser)
                .message("OK")
                .build();

            return new ResponseEntity<>(res, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(UserResponse
                .builder()
                .message("Something went wrong.")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR    
            );
        }
    }
}