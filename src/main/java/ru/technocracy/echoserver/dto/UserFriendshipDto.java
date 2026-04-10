package ru.technocracy.echoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendshipDto {

    private Long firstFriendId;
    private Long secondFriendId;

    private UserDto friendInfo;
    private String status;

    private boolean isIncoming;
}


