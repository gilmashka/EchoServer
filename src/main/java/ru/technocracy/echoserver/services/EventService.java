package ru.technocracy.echoserver.services;

import jakarta.transaction.Transactional;
import ru.technocracy.echoserver.dto.event.EventFromKudaGoDto;
import ru.technocracy.echoserver.dto.event.FullEventDto;
import ru.technocracy.echoserver.dto.event.ShortEventDto;
import ru.technocracy.echoserver.exceptions.BadRequestException;
import ru.technocracy.echoserver.models.notedevent.NoteType;

import java.util.List;

public interface EventService {

    List<ShortEventDto> getAllNotedEventsByUserIdWhereStatusIs(Long userId, NoteType noteType);

    void saveNotedEvent(Long userId, int eventId, NoteType noteType) throws BadRequestException;

    void deleteNotedEvent(Long userId, int eventId) throws BadRequestException;

    @Transactional
    FullEventDto getDetailsForEvent(int eventId);
}
