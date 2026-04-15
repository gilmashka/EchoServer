package ru.technocracy.echoserver.models;

import jakarta.persistence.*;
import lombok.*;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreference;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    @EqualsAndHashCode.Include
    private int idFromKudaGo;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserCategoryPreference> userCategoryPreferences;
}
