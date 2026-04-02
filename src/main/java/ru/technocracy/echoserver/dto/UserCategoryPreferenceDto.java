package ru.technocracy.echoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.usercategorypreference.PreferenceType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryPreferenceDto {

    private int id;
    private String name;
    private PreferenceType preference;
}
