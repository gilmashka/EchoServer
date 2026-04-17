package ru.technocracy.echoserver.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.dto.event.EventFromKudaGoDto;
import ru.technocracy.echoserver.dto.event.FullEventDto;
import ru.technocracy.echoserver.dto.event.PlaceFromKudaGo;
import ru.technocracy.echoserver.dto.event.ShortEventDto;
import ru.technocracy.echoserver.exceptions.BadRequestException;
import ru.technocracy.echoserver.exceptions.NotFoundException;
import ru.technocracy.echoserver.integrations.ExternalClient;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.models.notedevent.NoteType;
import ru.technocracy.echoserver.models.notedevent.NotedEvent;
import ru.technocracy.echoserver.models.notedevent.NotedEventPK;
import ru.technocracy.echoserver.repositories.NotedEventRepository;
import ru.technocracy.echoserver.repositories.UserRepository;
import ru.technocracy.echoserver.services.EventService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ExternalClient externalClient;
    private final NotedEventRepository notedEventRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<ShortEventDto> getAllNotedEventsByUserIdWhereStatusIs(Long userId, NoteType noteType) {

        User user = userRepository.getUserById(userId);
        if(user == null){
            throw new NotFoundException("Пользователь не найден");
        }

        List<Integer> idNotedEvents = notedEventRepository.getEventByUserIdWhereStatusIs(userId, noteType);

        return idNotedEvents.stream().map(externalClient::getEvent).toList();
    }

    @Transactional
    @Override
    public void saveNotedEvent(Long userId, int eventId, NoteType noteType) throws BadRequestException{

        EventFromKudaGoDto event = externalClient.checkEventExists(eventId);
        if(event == null){
            throw new NotFoundException("Данное мероприятие не найдено");
        }

        NotedEventPK PK = new NotedEventPK(userId, eventId);
        if(notedEventRepository.getNotedEventById(PK) == null){
            notedEventRepository.save(NotedEvent.builder()
                    .id(PK)
                    .noteType(noteType)
                    .build());
        } else {
            notedEventRepository.update(PK, noteType);
        }
    }

    @Transactional
    @Override
    public void deleteNotedEvent(Long userId, int eventId) throws BadRequestException{

        EventFromKudaGoDto event = externalClient.checkEventExists(eventId);
        if(event == null){
            throw new NotFoundException("Данное мероприятие не найдено");
        }

        NotedEventPK PK = new NotedEventPK(userId, eventId);
        if(notedEventRepository.getNotedEventById(PK) == null){
            throw new NotFoundException("Запись о взаимодействии не найдена");
        } else{
            notedEventRepository.deleteNotedEventById(PK);
        }
    }

    @Transactional
    @Override
    public FullEventDto getDetailsForEvent(int eventId) throws NotFoundException{
        EventFromKudaGoDto event = externalClient.getEventDetails(eventId);
        if(event == null){
            throw new NotFoundException("Данного события не существует");
        }

        PlaceFromKudaGo place = externalClient.getPlaceDetails(event.getPlaceId());

        return FullEventDto.builder()
                .place(place)
                .event(event)
                .build();

    }
}
