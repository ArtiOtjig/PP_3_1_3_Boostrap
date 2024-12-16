package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService{
    void save(User user, List<String> roles);
    List<User> getAllUsersWithRoles();
    void updateUser(User user, List<String> roles);
    void deleteUserById(int id);
    User findByEmail(String email);
}
