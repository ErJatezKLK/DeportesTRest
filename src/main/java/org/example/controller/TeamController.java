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

    @GetMapping(path = "teams")
    public ResponseEntity<List<TeamDto>> teams(
        @RequestParam(value = "sportId") Integer sportId
    ){
        List<TeamDto> teams = deportesService.getTemasByIdSport(sportId);
        return ResponseEntity.ok().body(teams);
    }
}
