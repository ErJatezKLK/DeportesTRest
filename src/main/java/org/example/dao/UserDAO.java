package org.example.dao;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
     boolean addUser(User entity);

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);
}