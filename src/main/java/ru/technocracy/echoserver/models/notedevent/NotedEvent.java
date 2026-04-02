package ru.technocracy.echoserver.models.notedevent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.Event;
import ru.technocracy.echoserver.models.User;

import java.net.UnknownServiceException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "noted_events")
public class NotedEvent {

    @EmbeddedId
    private NotedEventPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private NoteType noteType;
}
