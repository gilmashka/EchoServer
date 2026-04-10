package ru.technocracy.echoserver.services;

import ru.technocracy.echoserver.dto.FriendshipRequestForm;
import ru.technocracy.echoserver.dto.UserDto;
import ru.technocracy.echoserver.dto.UserFriendshipDto;
import ru.technocracy.echoserver.exceptions.BadRequestException;

import java.util.List;

public interface UserFriendshipService {

    List<UserDto> getAllFriends(Long userId);

    List<UserFriendshipDto> getAllIncomingRequests(Long userId);

    List<UserFriendshipDto> getAllOutcomingRequests(Long userId);

    void changeRequestStatus (FriendshipRequestForm friendshipRequestForm, Long currentUserId);
}
