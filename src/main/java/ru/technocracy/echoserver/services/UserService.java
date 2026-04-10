package ru.technocracy.echoserver.services;

import ru.technocracy.echoserver.dto.UserForm;
import ru.technocracy.echoserver.models.User;

public interface UserService {

    Long registerUser(UserForm userForm);

    User getUserByNickname(String name);
}
