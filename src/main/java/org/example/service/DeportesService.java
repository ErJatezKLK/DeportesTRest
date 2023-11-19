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
        User user;
        user = userDAO.getUserByEmailAndPassword(email, password);
        return user;
    }

    public User getUserByEmail(String email) {
        User user;
        user = userDAO.getUserByEmail(email);
        return user;
    }
}
