package ru.technocracy.echoserver.dto.event;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortEventDto {

    private Integer id;
    private String title;
    private String price;
    private List<EventDate> dates;

    private String imageUrl;

    @JsonSetter("images")
    public void setImages(List<Map<String, Object>> imagesList) {
        if (imagesList != null && !imagesList.isEmpty()) {
            this.imageUrl = (String) imagesList.get(0).get("image");
        }
    }
}


