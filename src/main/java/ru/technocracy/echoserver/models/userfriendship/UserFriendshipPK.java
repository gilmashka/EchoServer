package ru.technocracy.echoserver.models.userfriendship;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserFriendshipPK implements Serializable {

    private Long firstFriendId;

    private Long secondFriendId;

    public UserFriendshipPK(Long firstId, Long secondId){
        this.firstFriendId = min(firstId, secondId);
        this.secondFriendId = max(firstId, secondId);
    }
}
