package com.barbershop.manager.controllers;


import com.barbershop.manager.models.DTOs.user.AuthenticationDTO;
import com.barbershop.manager.models.DTOs.user.LoginResponseDTO;
import com.barbershop.manager.models.DTOs.user.UserInsertDTO;
import com.barbershop.manager.models.entities.User;
import com.barbershop.manager.services.TokenService;
import com.barbershop.manager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        try {
            System.out.println(">>> TENTANDO AUTENTICAR: " + data.login());

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (Exception e) {
            System.out.println(">>> ERRO NO LOGIN: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserInsertDTO data){
        var newUser = userService.insert(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


}
