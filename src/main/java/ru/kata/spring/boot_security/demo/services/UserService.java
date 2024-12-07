package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService{
    void save(User user);
    List<User> getAllUsers();
    Optional<User> findById(int id);
    void updateUser(User user);
    void deleteUserById(int id);
}
