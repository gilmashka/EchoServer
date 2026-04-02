package ru.technocracy.echoserver.models.usercategorypreference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.technocracy.echoserver.models.Category;
import ru.technocracy.echoserver.models.User;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_category_preference")
public class UserCategoryPreference {

    @EmbeddedId
    private UserCategoryPreferencePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private PreferenceType preferenceType;
}
