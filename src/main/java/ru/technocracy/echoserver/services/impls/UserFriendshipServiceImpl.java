package ru.technocracy.echoserver.services.impls;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.dto.FriendshipRequestForm;
import ru.technocracy.echoserver.dto.UserDto;
import ru.technocracy.echoserver.dto.UserFriendshipDto;
import ru.technocracy.echoserver.exceptions.BadRequestException;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.models.userfriendship.UserFriendship;
import ru.technocracy.echoserver.models.userfriendship.UserFriendshipPK;
import ru.technocracy.echoserver.repositories.UserFriendshipRepository;
import ru.technocracy.echoserver.repositories.UserRepository;
import ru.technocracy.echoserver.utils.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

import static ru.technocracy.echoserver.models.userfriendship.UserFriendshipInvitationStatus.*;

@Service
@RequiredArgsConstructor
public class UserFriendshipServiceImpl implements ru.technocracy.echoserver.services.UserFriendshipService {

    private final UserFriendshipRepository userFriendshipRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public List<UserDto> getAllFriends(Long userId){

        List<User> users = userFriendshipRepository.findAllAcceptedFriends(userId);
        return users.stream()
                .map(userMapper::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<UserFriendshipDto> getAllIncomingRequests(Long userId) {
       List<UserFriendship> userFriendships = userFriendshipRepository.findIncomingRequests(userId);

       return userFriendships.stream().map(
                userFriendship -> {

                    User user;
                    if(userFriendship.getFirstFriend().getId().equals(userId)){
                        user = userFriendship.getSecondFriend();
                    }
                    else {
                        user = userFriendship.getFirstFriend();
                    }

                    return UserFriendshipDto.builder()
                            .firstFriendId(userFriendship.getFirstFriend().getId())
                            .secondFriendId(userFriendship.getSecondFriend().getId())
                            .friendInfo(userMapper.fromUserToUserDto(user))
                            .status(userFriendship.getUserFriendshipInvitationStatus().name())
                            .isIncoming(true)
                            .build();
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<UserFriendshipDto> getAllOutcomingRequests(Long userId) {
        List<UserFriendship> userFriendships = userFriendshipRepository.findOutgoingRequests(userId);

        return userFriendships.stream().map(
                userFriendship -> {

                    User user;
                    if(userFriendship.getFirstFriend().getId().equals(userId)){
                        user = userFriendship.getSecondFriend();
                    }
                    else {
                        user = userFriendship.getFirstFriend();
                    }

                    return UserFriendshipDto.builder()
                            .firstFriendId(userFriendship.getFirstFriend().getId())
                            .secondFriendId(userFriendship.getSecondFriend().getId())
                            .friendInfo(userMapper.fromUserToUserDto(user))
                            .status(userFriendship.getUserFriendshipInvitationStatus().name())
                            .isIncoming(false)
                            .build();
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void changeRequestStatus (FriendshipRequestForm friendshipRequestForm, Long currentUserId) throws BadRequestException {

        if(friendshipRequestForm.getRecipientId().equals(currentUserId)){
            throw new BadRequestException("Невозможно отправить заявку самому себе");
        }

        UserFriendshipPK userFriendshipPK = new UserFriendshipPK(currentUserId,
                friendshipRequestForm.getRecipientId());
        UserFriendship userFriendship = userFriendshipRepository.getUserFriendshipById(userFriendshipPK);

        if(userFriendship == null){
            userFriendship = UserFriendship.builder()
                    .id(userFriendshipPK)
                    .firstFriend(userRepository.getReferenceById(userFriendshipPK.getFirstFriendId()))
                    .secondFriend(userRepository.getReferenceById(userFriendshipPK.getSecondFriendId()))
                    .sender(userRepository.getReferenceById(currentUserId))
                    .userFriendshipInvitationStatus(SENT)
                    .build();

            userFriendshipRepository.save(userFriendship);
            return;
        }
        else {
            userFriendship.setSender(userRepository.getReferenceById(currentUserId));
            userFriendship.setUserFriendshipInvitationStatus(friendshipRequestForm.getUserFriendshipInvitationStatus());
            userFriendshipRepository.save(userFriendship);
            return;
        }
    }

}
