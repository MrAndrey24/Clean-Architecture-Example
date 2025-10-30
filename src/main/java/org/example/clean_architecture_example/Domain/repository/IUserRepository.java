package org.example.clean_architecture_example.Domain.repository;

import org.example.clean_architecture_example.Domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
