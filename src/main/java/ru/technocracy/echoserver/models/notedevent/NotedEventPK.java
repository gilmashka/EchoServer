package ru.technocracy.echoserver.models.notedevent;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NotedEventPK implements Serializable {

    private Long userId;

    private Long eventId;
}
