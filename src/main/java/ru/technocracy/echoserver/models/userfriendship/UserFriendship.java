package ru.technocracy.echoserver.models.userfriendship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_friendships")
public class UserFriendship {

    @EmbeddedId
    private UserFriendshipPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("firstFriendId")
    @JoinColumn(name = "first_friend_id")
    private User firstFriend;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("secondFriendId")
    @JoinColumn(name = "second_friend_id")
    private User secondFriend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Enumerated(EnumType.STRING)
    private UserFriendshipInvitationStatus userFriendshipInvitationStatus;
}
