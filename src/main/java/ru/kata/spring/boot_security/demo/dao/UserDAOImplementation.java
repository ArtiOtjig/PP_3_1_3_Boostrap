package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.arti.otjig.PP_3_1_1_CRUD_SpringBoot.models.User;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class UserDAOImplementation implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        Optional<User> deletedUser = findById(id);
        deletedUser.ifPresent(user -> entityManager.remove(user));
    }
}
