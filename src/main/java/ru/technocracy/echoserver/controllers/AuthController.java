package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.technocracy.echoserver.dto.UserForm;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.repositories.UserRepository;
import ru.technocracy.echoserver.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody UserForm userForm){
        Long id = userService.registerUser(userForm);
        return ResponseEntity.status(201).body(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(Principal principal){
        User user = userRepository.findByNickname(principal.getName());
        return ResponseEntity.status(200).body(user.getId());
    }
}
