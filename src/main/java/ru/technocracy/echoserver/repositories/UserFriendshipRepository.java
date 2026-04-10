package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.models.userfriendship.UserFriendship;
import ru.technocracy.echoserver.models.userfriendship.UserFriendshipPK;

import java.util.List;

public interface UserFriendshipRepository extends JpaRepository<UserFriendship, UserFriendshipPK> {

    @Query("SELECT f FROM UserFriendship f WHERE (f.id.firstFriendId = :id OR f.id.secondFriendId = :id) " +
            "AND f.userFriendshipInvitationStatus = 'PENDING' AND f.sender.id != :id")
    List<UserFriendship> findIncomingRequests(@Param("id") Long id);

    @Query("SELECT f FROM UserFriendship f WHERE (f.id.firstFriendId = :id OR f.id.secondFriendId = :id) " +
            "AND f.userFriendshipInvitationStatus = 'PENDING' AND f.sender.id = :id")
    List<UserFriendship> findOutgoingRequests(@Param("id") Long id);

    @Query("SELECT CASE " +
            "  WHEN f.id.firstFriendId = :id THEN f.secondFriend " +
            "  ELSE f.firstFriend END " +
            "FROM UserFriendship f " +
            "WHERE (f.id.firstFriendId = :id OR f.id.secondFriendId = :id) " +
            "AND f.userFriendshipInvitationStatus = 'ACCEPTED'")
    List<User> findAllAcceptedFriends(@Param("id") Long id);

    UserFriendship getUserFriendshipById(UserFriendshipPK id);
}
