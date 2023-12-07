package org.example.controller;

import org.example.controller.dto.TeamDto;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private DeportesService deportesService;

    /**
     * Busca por equipo por id de deporte
     * @param sportId
     * @return
     */
    @GetMapping(path = "/teams")
    public ResponseEntity<List<TeamDto>> teams(
        @RequestParam(value = "sportId") Integer sportId
    ){
        List<TeamDto> teams = deportesService.getTeamsByIdSport(sportId);
        return ResponseEntity.ok().body(teams);
    }


    /*
    @GetMapping(path = "/team-athlete")
    public ResponseEntity<TeamDto> teamWithAthletes(
            @RequestParam(value = "teamId") Integer teamdId
    ){
        TeamDto teamDto = deportesService.getTeamWithMembers(teamdId);
        return ResponseEntity.ok().body(teamDto);
    }

     */


}
