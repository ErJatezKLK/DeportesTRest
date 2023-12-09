package org.example.controller;

import org.example.controller.dto.AthleteDto;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AthleteController {

    @Autowired
    private DeportesService deportesService;

    @GetMapping("/athletes")
    public ResponseEntity<List<AthleteDto>> getAthletesList(
            @RequestParam(value = "sportId") Integer sportId
    ){
        List<AthleteDto> athleteDtos = deportesService.getTenisPlayersByIdSport(sportId);
        return ResponseEntity.ok().body(athleteDtos);
    }

    @GetMapping("/atheletes-team")
    public ResponseEntity<List<AthleteDto>> getAthleteByTeamId(
        @RequestParam(value = "teamId") Integer teamId
    ){
        List<AthleteDto> athleteDtos = deportesService.getAthletesByTeamId(teamId);
        return ResponseEntity.ok().body(athleteDtos);
    }
}
