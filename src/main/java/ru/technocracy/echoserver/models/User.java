package ru.technocracy.echoserver.models;


import jakarta.persistence.*;
import lombok.*;
import ru.technocracy.echoserver.models.notedevent.NotedEvent;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreference;
import ru.technocracy.echoserver.models.userfriendship.UserFriendship;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accounts")
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String firstName;

    private String lastName;

    private String avatarPath;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserCategoryPreference> userCategoryPreferences;

    @OneToMany(mappedBy = "firstFriend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFriendship> userFriendshipsAsFirst = new ArrayList<>();

    @OneToMany(mappedBy = "secondFriend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFriendship> userFriendshipsAsSecond = new ArrayList<>();

    public List<UserFriendship> getAllUserFriendships(){
        List<UserFriendship> allUserFriendships = new ArrayList<>();
        allUserFriendships.addAll(userFriendshipsAsFirst);
        allUserFriendships.addAll(userFriendshipsAsSecond);
        return allUserFriendships;
    }

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFriendship> userInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NotedEvent> notedEvents = new ArrayList<>();
}
