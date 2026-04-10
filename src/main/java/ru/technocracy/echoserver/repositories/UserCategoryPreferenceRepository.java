package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreference;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreferencePK;

import java.util.List;
import java.util.Optional;

public interface UserCategoryPreferenceRepository extends JpaRepository<UserCategoryPreference, UserCategoryPreferencePK> {

    List<UserCategoryPreference> findAllById_UserId(Long userId);

    Optional<UserCategoryPreference> findByUserIdAndCategoryIdFromKudaGo(Long userId, int categoryId);

}
