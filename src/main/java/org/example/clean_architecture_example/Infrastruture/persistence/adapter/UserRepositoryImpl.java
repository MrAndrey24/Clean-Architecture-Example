package org.example.clean_architecture_example.Infrastruture.persistence.adapter;

import org.example.clean_architecture_example.Domain.entities.User;
import org.example.clean_architecture_example.Domain.repository.IUserRepository;
import org.example.clean_architecture_example.Infrastruture.mapper.UserMapper;
import org.example.clean_architecture_example.Infrastruture.persistence.repository.UserRepositoryJpa;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final UserRepositoryJpa repository;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserRepositoryJpa repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public User save(User user) {
        UserEntity entity = mapper.domainToEntity(user);
        return mapper.entityToDomain(repository.save(entity));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::entityToDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::entityToDomain);
    }


    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToDomain);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}