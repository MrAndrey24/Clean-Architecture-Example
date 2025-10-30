package org.example.clean_architecture_example.Domain.service;

import org.example.clean_architecture_example.Domain.entities.Role;

public interface IRoleService {
    Long save(Role role);
}
