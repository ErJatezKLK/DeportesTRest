package org.example.controller;

import org.example.controller.dto.SportDto;
import org.example.entity.SportEntity;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SportController {

    @Autowired
    private DeportesService deportesService;

    /**
     * Devuelve todos los deportes
     * @return
     */
    @GetMapping(path = "/sports")
    public ResponseEntity<List<SportDto>> sports() {
        List<SportEntity> sports = deportesService.getAllSports();
        List<SportDto> sportDtos = new ArrayList<>();
        for (SportEntity sport: sports) {
            SportDto sportDto = SportDto.toDto(sport);
            sportDtos.add(sportDto);
        }
        System.out.println(sportDtos);
        return ResponseEntity.ok(sportDtos);
    }
}
