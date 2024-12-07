package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Role> getRolesList() {
        return rolesRepository.findAll();
    }

    public Optional<Role> getRoleById(long id) {
        return rolesRepository.findById(id);
    }


}
