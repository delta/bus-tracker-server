package edu.nitt.delta.bustracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.model.User;
import edu.nitt.delta.bustracker.repository.UserRepository;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllDriver() {
        return userRepository.findByRole(Role.DRIVER);
    }

    public User getDriverById(String id) {
        Optional<User> driver = userRepository.findById(id);
        return driver.orElse(null);
    }

    public User insertUser(User user) {
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        User insertedUser = userRepository.insert(user);

        return User.builder()
            .id(insertedUser.getId())
            .firstName(insertedUser.getFirstName())
            .lastName(insertedUser.getLastName())
            .mobileNumber(insertedUser.getMobileNumber())
            .role(insertedUser.getRole())
            .build();
    }
}
