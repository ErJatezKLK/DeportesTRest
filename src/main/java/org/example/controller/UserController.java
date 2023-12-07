package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.entity.UserEntity;
import org.example.exceptions.UserNotFoundException;
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


    /**
     * Recibe dos parametros y busca el usuario
     * @param email
     * @param password
     * @return
     */
    @GetMapping(path = "/user")
    public ResponseEntity<UserDto> user(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ) {
        UserEntity user = deportesService.getUserByEmailAndPassword(email, password);
        UserDto userDto = UserDto.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Busca un usuario por email este se utiliza para cargar el usuario en el drawer de Android
     * @param email
     * @return
     */
    @GetMapping(path = "/user-email")
    public ResponseEntity<UserDto> userEmail(
            @RequestParam(value = "email") String email
    ) {
        UserEntity user = deportesService.getUserByEmail(email);
        UserDto userDto = UserDto.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Recibe un usuario para guardarlo en la base de datos
     * @param userDto
     * @return
     */
    @PostMapping(path = "/user-add")
    public ResponseEntity<Void> addUser(
            @Valid @RequestBody UserEntity userDto
    ){
        if (deportesService.addUser(userDto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Borra el usuario por email e endpoint funciona pero
     * en android no
     * @param email
     * @return
     */
    @DeleteMapping("/user-del")
    public ResponseEntity<Void> deleteUserByEmail(
            @RequestParam(value = "email") String email
    ) {
        deportesService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    /**
     * Cambia la contraseña del usuario
     * @param userDto
     * @param newPassword
     * @return
     */
    @PutMapping("/user-update")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody UserEntity user,
            @RequestParam(value = "newPassword") String newPassword
    ) throws UserNotFoundException {

        if (deportesService.addUserWithNewPassword(user, newPassword)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
