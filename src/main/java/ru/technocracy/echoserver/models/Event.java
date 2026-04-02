package ru.technocracy.echoserver.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.notedevent.NotedEvent;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    private Long id;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NotedEvent> notedEvents = new ArrayList<>();

}
