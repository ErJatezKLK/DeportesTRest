package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.entity.User;
import org.example.service.DeportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping(path = "/user")
    public ResponseEntity<UserDto> userEmail(
            @RequestParam(value = "email") String email
    ) {
        User user = deportesService.getUserByEmail(email);
        UserDto userDto = UserDto.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Void> addUSer(
            @Valid @RequestBody UserDto userDto
    ){
        if (deportesService.addUser(UserDto.toEntity(userDto))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
