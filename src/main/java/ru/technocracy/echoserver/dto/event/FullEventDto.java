package ru.technocracy.echoserver.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullEventDto {

    private EventFromKudaGoDto event;
    private PlaceFromKudaGo place;
    private List<EventImageDto> images;
}
