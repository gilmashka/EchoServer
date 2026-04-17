package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.exceptions.NotFoundException;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.services.EventService;
import ru.technocracy.echoserver.services.FeedService;
import ru.technocracy.echoserver.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final EventService eventService;
    private final UserService userService;

    @GetMapping("/favourite")
    public ResponseEntity<?> getFavouriteFeed(Principal principal){
        User user = userService.getUserByNickname(principal.getName());

        try {
            return ResponseEntity.status(200).body(feedService.getUserFavoritesEventFeed(user.getId()));
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/neutral")
    public ResponseEntity<?> getNeutralFeed(Principal principal){
        User user = userService.getUserByNickname(principal.getName());

        try {
            return ResponseEntity.status(200).body(feedService.getUserNeutralEventFeed(user.getId()));
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/details/{eventId}")
    public ResponseEntity<?> getEventDetails(@PathVariable int eventId){
        try{
            return ResponseEntity.status(200).body(eventService.getDetailsForEvent(eventId));
        }catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
