package org.example.clean_architecture_example.Domain.service;

import org.example.clean_architecture_example.Domain.entities.User;

import java.util.List;

public interface IUserService {
    Long save(User user);
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    void updateUser(User user);


}
