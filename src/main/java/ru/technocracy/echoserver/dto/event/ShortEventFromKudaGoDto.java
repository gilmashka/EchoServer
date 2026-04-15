package ru.technocracy.echoserver.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortEventFromKudaGoDto {

    private int id;
    private String title;
    private String price;
    private List<String> images;
    private List<EventDate> dates;


}


