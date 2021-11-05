package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.controller.response.DriverResponse;
import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.model.User;
import edu.nitt.delta.bustracker.repository.UserRepository;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<DriverResponse> getAllDriver() {
        return userRepository.findByRole(Role.DRIVER);
    }

    public DriverResponse getDriverById(String id) {
        Optional<User> driver = userRepository.findById(id);
        if(driver.isPresent()) {
            DriverResponse driverResponse = DriverResponse.builder()
                .id(driver.get().getId())
                .firstName(driver.get().getFirstName())
                .lastName(driver.get().getLastName())
                .mobileNumber(driver.get().getMobileNumber())
                .build();

            return driverResponse;
        }
        return null;
    }

    public DriverResponse insertUser(User user) {
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        User insertedUser = userRepository.insert(user);

        return DriverResponse.builder()
            .id(insertedUser.getId())
            .firstName(insertedUser.getFirstName())
            .lastName(insertedUser.getLastName())
            .mobileNumber(insertedUser.getMobileNumber())
            .build();
    }

    public User getDriverByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber).orElse(null);
    }
}
