package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.technocracy.echoserver.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    User getUserById(Long id);
}
