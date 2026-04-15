package ru.technocracy.echoserver.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFromKudaGoDto {

    private int id;
    private String title;
    private String bodyText;
    private String location;
    private String price;
    private String siteUrl;
    private List<EventDate> dates;
    private Place placeId;
    private List<String> images;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Place{
    int id;
}
