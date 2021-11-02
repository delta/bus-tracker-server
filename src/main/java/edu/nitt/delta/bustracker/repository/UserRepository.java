package edu.nitt.delta.bustracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.nitt.delta.bustracker.controller.response.DriverResponse;
import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    public List<DriverResponse> findByRole(Role role);
    public Optional<User> findById(String id);
    public Optional<User> findByMobileNumber(String mobileNumber);
}
