package org.example.controller;

import org.example.controller.dto.EventDto;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private DeportesService deportesService;

    @GetMapping(path = "/event")
    public ResponseEntity<List<EventDto>> getEventsBySportId(
            @RequestParam(value = "sportId") Integer sportId
    ){
        List<EventDto> eventDtos = deportesService. getEventsBySportId(sportId);
        return ResponseEntity.ok().body(eventDtos);
    }
}
