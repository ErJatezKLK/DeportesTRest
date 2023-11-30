package org.example.service;

import org.example.controller.dto.EventDto;
import org.example.controller.dto.TeamDto;
import org.example.controller.dto.UserDto;
import org.example.dao.*;
import org.example.entity.EventSport;
import org.example.entity.Sport;
import org.example.entity.Team;
import org.example.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeportesService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SportDAO sportDAO;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    EventDAO eventDAO;

    @Autowired
    private AthleteDAO athleteDAO;

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

    public List<TeamDto> getTemasByIdSport(Integer sportId) {
        List<Team> teams = teamDao.findTeamBySportId(sportId);
        teams.forEach(team -> {
            team.getAthletes().isEmpty();
        });
        List<TeamDto> teamDtos = new ArrayList<>();

        for (Team team : teams) {
            TeamDto teamDto = TeamDto.toDto(team);
            teamDtos.add(teamDto);
        }

        return teamDtos;
    }

    public List<EventDto> getEventsBySportId(Integer sportId) {
        List<EventSport> eventSports = eventDAO.findEventBySportId(sportId);
        eventSports.forEach(eventSport -> {
            eventSport.getSport().getName().isEmpty();
        });
        List<EventDto> eventDtos = new ArrayList<>();

        for (EventSport event : eventSports) {
            EventDto eventDto = EventDto.toDto(event);
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    public UserDto findByEmail(String email) {
        return UserDto.toDto(userDAO.getUserByEmail(email));
    }

}
