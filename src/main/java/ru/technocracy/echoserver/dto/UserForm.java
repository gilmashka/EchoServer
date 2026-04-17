package ru.technocracy.echoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.City;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String nickname;
    private String firstName;
    private String lastName;
    private String password;
    private City city;
}
