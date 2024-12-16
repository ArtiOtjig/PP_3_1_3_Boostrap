package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsersWithRoles() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Hibernate.initialize(user.getRoles());
        }
        return users;
    }
    @Override
    @Transactional
    public void save(User user, List<String> roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = roles.stream().map(Long::valueOf).map(rolesRepository::findById)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, List<String> roles) {
        Set<Role> roleSet = roles.stream().map(Long::valueOf).map(rolesRepository::findById)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
        if (user.getPassword().isEmpty()){
            user.setPassword(bCryptPasswordEncoder.encode(
                    userRepository.findById(user.getId()).get().getPassword()));
        }
        user.setRoles(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }
}
