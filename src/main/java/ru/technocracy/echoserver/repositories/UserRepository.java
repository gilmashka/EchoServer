package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.technocracy.echoserver.models.City;
import ru.technocracy.echoserver.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    User getUserById(Long id);

    @Query("SELECT u.city FROM User u WHERE u.id =: userId")
    City getCityByUserId(@Param("userId") Long id);
}
