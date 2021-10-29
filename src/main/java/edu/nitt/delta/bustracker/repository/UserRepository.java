package edu.nitt.delta.bustracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(fields = "{ 'password' : 0 }")
    public List<User> findByRole(Role role);

    @Query(fields = "{ 'password' : 0 }")
    public Optional<User> findById(String id);

    public Optional<User> findByMobileNumber(String mobileNumber);
}
