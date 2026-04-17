package ru.technocracy.echoserver.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public @Builder
@AllArgsConstructor
@NoArgsConstructor
class EventDate{

    private Long start;
    private Long end;

    public LocalDateTime getStartDateTime() {
        return Instant.ofEpochSecond(start)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return Instant.ofEpochSecond(end)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}