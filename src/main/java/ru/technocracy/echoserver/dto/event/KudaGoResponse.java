package ru.technocracy.echoserver.dto.event;

import lombok.Data;

import java.util.List;

@Data
public class KudaGoResponse {
    private int count;
    private String next;
    private String previous;
    private List<ShortEventDto> results;  // ← массив внутри results
}