
// Controlador
package com.example.sportsbooking.controller;

import com.example.sportsbooking.model.User;
import com.example.sportsbooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}
