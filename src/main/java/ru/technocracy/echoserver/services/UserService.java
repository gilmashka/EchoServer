package ru.technocracy.echoserver.services;

import jakarta.transaction.Transactional;
import ru.technocracy.echoserver.dto.UserForm;
import ru.technocracy.echoserver.models.City;
import ru.technocracy.echoserver.models.User;

public interface UserService {

    Long registerUser(UserForm userForm);

    User getUserByNickname(String name);

    @Transactional
    City getUserCityById(Long userId);
}
