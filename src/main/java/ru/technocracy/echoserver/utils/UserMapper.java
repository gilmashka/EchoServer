package ru.technocracy.echoserver.utils;

import org.springframework.stereotype.Component;
import ru.technocracy.echoserver.dto.UserDto;
import ru.technocracy.echoserver.models.User;

@Component
public class UserMapper {

    public UserDto fromUserToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarPath(user.getAvatarPath())
                .build();
    }
}
