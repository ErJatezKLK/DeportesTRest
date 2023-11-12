package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.entity.User;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private DeportesService deportesService;

    @GetMapping(path = "/user")
    public ResponseEntity<UserDto> user(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password

    ) {
        User user = deportesService.getUserByEmailAndPassword(email, password);
        UserDto userDto = UserDto.toDto(user);
        return ResponseEntity.ok(userDto);
    }


}
