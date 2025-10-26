package org.example.springsecuritycrud.Domain.service;

import org.example.springsecuritycrud.Domain.entities.User;

import java.util.List;

public interface IUserService {
    Long save(User user);
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    void updateUser(User user);


}
