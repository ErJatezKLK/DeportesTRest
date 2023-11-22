package org.example.service;

import org.example.dao.SportDAO;
import org.example.dao.UserDAO;
import org.example.entity.Sport;
import org.example.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Positive;
import java.util.List;

@Service
public class DeportesService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SportDAO sportDAO;

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

    public boolean addUser(User user) {
        if (!userDAO.existsByEmail(user.getEmail().toLowerCase())) {
            user.setEmail(user.getEmail().toLowerCase());
            user.setEmail(user.getEmail().toLowerCase());
            userDAO.save(user);
            return true;
        } else {
            return false;
        }
    }

    public List<Sport> getAllSports() {
        return sportDAO.findAll();
    }

    public void deleteUserByEmail(String email) {
        userDAO.deleteByEmail(email);
    }
}
