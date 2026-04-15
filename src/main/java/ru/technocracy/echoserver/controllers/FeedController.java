package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.exceptions.NotFoundException;
import ru.technocracy.echoserver.services.EventService;
import ru.technocracy.echoserver.services.FeedService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final EventService eventService;

    @GetMapping("/favourite/{userId}")
    public ResponseEntity<?> getFavouriteFeed(@PathVariable Long userId){
        try {
            return ResponseEntity.status(200).body(feedService.getUserFavoritesEventFeed(userId));
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/neutral/{userId}")
    public ResponseEntity<?> getNeutralFeed(@PathVariable Long userId){
        try {
            return ResponseEntity.status(200).body(feedService.getUserNeutralEventFeed(userId));
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
