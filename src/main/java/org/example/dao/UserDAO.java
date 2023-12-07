package org.example.dao;


import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {

    UserEntity getUserByEmailAndPassword(String email, String password);

    UserEntity getUserByEmail(String email);

    boolean existsByEmail(String lowerCase);

    void deleteByEmail(String email);
}