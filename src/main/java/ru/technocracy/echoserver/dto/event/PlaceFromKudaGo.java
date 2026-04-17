package ru.technocracy.echoserver.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceFromKudaGo {

    private int id;
    private String title;
    private String address;
    private String foreignUrl;
}
