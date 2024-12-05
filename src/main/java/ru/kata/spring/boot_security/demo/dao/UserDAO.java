package ru.kata.spring.boot_security.demo.dao;

import ru.arti.otjig.PP_3_1_1_CRUD_SpringBoot.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void save(User user);
    List<User> getAllUsers();
    Optional<User> findById(int id);
    void updateUser(User user);
    void deleteUserById(int id);

}
