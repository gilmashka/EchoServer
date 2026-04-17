package ru.technocracy.echoserver.dto.event;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageDto {
    private String imageUrl;
    private String sourceName;
    private String sourceLink;

    @JsonSetter("image")
    public void setImage(String image) {
        this.imageUrl = image;
    }

    @JsonSetter("source")
    public void setSource(Map<String, String> source) {
        if (source != null) {
            this.sourceName = source.get("name");
            this.sourceLink = source.get("link");
        }
    }
}