package org.example.service;

import org.example.dao.UserDAO;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeportesService {

    @Autowired
    private UserDAO userDAO;

    public User getUserByEmailAndPassword(
            String email, String password
    ) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }

}
