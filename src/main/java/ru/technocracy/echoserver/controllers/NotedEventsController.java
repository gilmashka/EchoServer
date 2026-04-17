package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.dto.event.ShortEventDto;
import ru.technocracy.echoserver.exceptions.NotFoundException;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.models.notedevent.NoteType;
import ru.technocracy.echoserver.services.EventService;
import ru.technocracy.echoserver.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/noted")
@RequiredArgsConstructor
public class NotedEventsController {

    private final EventService eventService;
    private final UserService userService;

    @GetMapping("/like")
    public ResponseEntity<?> getLikedEvents(Principal principal){

        User user = userService.getUserByNickname(principal.getName());

        try{
            List<ShortEventDto> events = eventService.getAllNotedEventsByUserIdWhereStatusIs(user.getId(), NoteType.LIKE);
            return ResponseEntity.status(200).body(events);
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/willgo")
    public ResponseEntity<?> gerWillgoEvents(Principal principal){

        User user = userService.getUserByNickname(principal.getName());

        try{
            List<ShortEventDto> events = eventService.getAllNotedEventsByUserIdWhereStatusIs(user.getId(), NoteType.WILLGO);
            return ResponseEntity.status(200).body(events);
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/create/{eventId}")
    public ResponseEntity<?> noteEvent(Principal principal, @PathVariable("eventId") int eventId, @RequestBody NoteType noteType){

        User user = userService.getUserByNickname(principal.getName());

        try{
            eventService.saveNotedEvent(user.getId(), eventId, noteType);
            return ResponseEntity.status(201).build();
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{eventId}")
    public ResponseEntity<?> deleteNotedEvent(Principal principal, @PathVariable("eventId") int eventId){

        User user = userService.getUserByNickname(principal.getName());

        try{
            eventService.deleteNotedEvent(user.getId(), eventId);
            return ResponseEntity.status(204).build();
        } catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
