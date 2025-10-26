package org.example.springsecuritycrud.Application.service;

import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Domain.repository.IUserRepository;
import org.example.springsecuritycrud.Domain.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;
    private  final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        if (findById(user.getId()) != null) {
            repository.save(user);
        }
    }
}
