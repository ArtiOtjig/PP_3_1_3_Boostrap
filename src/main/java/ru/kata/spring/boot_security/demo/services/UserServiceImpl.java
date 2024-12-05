package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arti.otjig.PP_3_1_1_CRUD_SpringBoot.dao.UserDAO;
import ru.arti.otjig.PP_3_1_1_CRUD_SpringBoot.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Optional<User> findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }
}
