package org.example.service;

import org.example.controller.dto.AthleteDto;
import org.example.controller.dto.EventDto;
import org.example.controller.dto.TeamDto;
import org.example.dao.*;
import org.example.entity.*;

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
    private EventDAO eventDAO;

    @Autowired
    private AthleteDAO athleteDAO;

    /**
     * Metodo que llama al dao para pedir un usuario
     * por email y contraseña
     * @param email
     * @param password
     * @return
     */
    public UserEntity getUserByEmailAndPassword(
            String email, String password
    ) {
        UserEntity user;
        user = userDAO.getUserByEmailAndPassword(email, password);
        return user;
    }

    /**
     * Metodo que llama al dao para pedir un usuario
     * por email
     * @param email
     * @return
     */
    public UserEntity getUserByEmail(String email) {
        UserEntity user;
        user = userDAO.getUserByEmail(email);
        return user;
    }

    /**
     * Llama al dao para crear un usuario
     * @param user
     * @return
     */
    public boolean addUser(UserEntity user) {
        if (!userDAO.existsByEmail(user.getEmail().toLowerCase())) {
            user.setEmail(user.getEmail().toLowerCase());
            user.setEmail(user.getEmail().toLowerCase());
            userDAO.save(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Llama al dao para pedir todos los deportes
     * @return
     */
    public List<SportEntity> getAllSports() {
        return sportDAO.findAll();
    }

    /**
     * Llama al dao para borrar un usuario por email
     * @param email
     */
    public void deleteUserByEmail(String email) {
        userDAO.deleteByEmail(email);
    }

    /**
     * Busca todos los equipos por id de deporte
     * @param sportId
     * @return
     */
    public List<TeamDto> getTeamsByIdSport(Integer sportId) {
        List<TeamEntity> teams = teamDao.findTeamBySportId(sportId);
        teams.forEach(team -> {
            team.getAthletes().isEmpty();
        });
        List<TeamDto> teamDtos = new ArrayList<>();

        for (TeamEntity team : teams) {
            TeamDto teamDto = TeamDto.toDto(team);
            teamDtos.add(teamDto);
        }
        return teamDtos;
    }

    /**
     * Busca todos los eventos por id de deporte
     * @param sportId
     * @return
     */
    public List<EventDto> getEventsBySportId(Integer sportId) {
        List<EventSportEntity> eventSports = eventDAO.findEventBySportId(sportId);
        eventSports.forEach(eventSport -> {
            eventSport.getSport().getName().isEmpty();
        });
        List<EventDto> eventDtos = new ArrayList<>();

        for (EventSportEntity event : eventSports) {
            EventDto eventDto = EventDto.toDto(event);
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    /**
     * Cambia la contraseña de usuario
     *
     * @param user
     * @param newPassword
     * @return
     */
    public boolean addUserWithNewPassword(UserEntity user, String newPassword) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(newPassword);
            userDAO.save(user);
            return true;
        } else {
            return false;
        }
    }

    public List<AthleteDto> getTenisPlayersByIdSport(Integer sportId) {
        List<AthleteEntity> athleteEntities = athleteDAO.findBySportId(sportId);
        athleteEntities.forEach(athleteEntity -> {
            athleteEntity.getName().isEmpty();
        });

        List<AthleteDto> athleteDtos = new ArrayList<>();

        for (AthleteEntity athleteEntity: athleteEntities) {
            AthleteDto athleteDto = AthleteDto.toDto(athleteEntity);
            athleteDtos.add(athleteDto);
        }
        return athleteDtos;

    }

    public List<AthleteDto> getAthletesByTeamId(Integer teamId) {
         List<AthleteEntity> athleteEntities = athleteDAO.findByTeamId(teamId);
        athleteEntities.forEach(athleteEntity -> {
            athleteEntity.getName().isEmpty();
        });

        List<AthleteDto> athleteDtos = new ArrayList<>();

        for (AthleteEntity athleteEntity: athleteEntities) {
            AthleteDto athleteDto = AthleteDto.toDto(athleteEntity);
            athleteDtos.add(athleteDto);
        }
        return athleteDtos;
    }


    //Este da el error de abajo
    //Solo da el error si intento pedir un equipo con sus jugadores el error esta abajo 
    public TeamDto getTeamWithMembers(Integer teamId) {
        TeamEntity team = teamDao.getTeamWithMembersById(teamId);
        team.getAthletes().forEach(athleteEntity -> {
            athleteEntity.getName().isEmpty();
        });
        return TeamDto.toDto(team);
    }

    /*

    El caso es que parece un bucle el cual busca el equipo y los deportistas una y otra vez
    llega un punto en el que aborta para no implosionar

    Este error solo sale en caso de poner lo siguiente en el dto
    private List<AthleteDto> athleteDtoList;

    Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception
    [Request processing failed; nested exception is org.springframework.http.converter.
    HttpMessageNotWritableException: Could not write JSON: Infinite recursion (StackOverflowError);
    nested exception is com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion
    (StackOverflowError) (through reference chain: org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]-
    >org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]-
    >org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->
    org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->
    org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"]->
    org.hibernate.collection.internal.PersistentBag[0]->org.example.entity.AthleteEntity["team"]->org.example.entity.TeamEntity["athletes"])] with root cause
  */


    
   



}
