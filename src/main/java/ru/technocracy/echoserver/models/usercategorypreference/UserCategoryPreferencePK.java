package ru.technocracy.echoserver.models.usercategorypreference;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryPreferencePK implements Serializable {

    private Long userId;

    private int categoryId;
}
