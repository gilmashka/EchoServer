package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.dto.FriendshipRequestForm;
import ru.technocracy.echoserver.dto.UserFriendshipDto;
import ru.technocracy.echoserver.exceptions.BadRequestException;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.services.UserFriendshipService;
import ru.technocracy.echoserver.services.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final UserFriendshipService userFriendshipService;
    private final UserService userService;

    @GetMapping("/incoming")
    public ResponseEntity<List<UserFriendshipDto>> getAllIncomingRequests(Principal principal){

        User user = userService.getUserByNickname(principal.getName());

        return ResponseEntity.status(200)
                .body(userFriendshipService.getAllIncomingRequests(user.getId()));
    }

    @GetMapping("/outcoming")
    public ResponseEntity<List<UserFriendshipDto>> getAllOutcomingRequests(Principal principal){

        User user = userService.getUserByNickname(principal.getName());

        return ResponseEntity.status(200)
                .body(userFriendshipService.getAllOutcomingRequests(user.getId()));
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestFriend(
            @RequestBody FriendshipRequestForm friendshipRequestForm,
                         Principal principal) {
        User user = userService.getUserByNickname(principal.getName());

        try{
            userFriendshipService.changeRequestStatus(friendshipRequestForm, user.getId());
        } catch (BadRequestException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(201).build();

    }
}
