package ru.technocracy.echoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.userfriendship.UserFriendshipInvitationStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipRequestForm {

    private Long recipientId;
    private UserFriendshipInvitationStatus userFriendshipInvitationStatus;

}
