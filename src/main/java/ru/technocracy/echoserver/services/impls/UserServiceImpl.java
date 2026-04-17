package ru.technocracy.echoserver.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.dto.UserForm;
import ru.technocracy.echoserver.models.City;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.repositories.UserRepository;
import ru.technocracy.echoserver.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long registerUser(UserForm userForm){
        User user = User.builder()
                .nickname(userForm.getNickname())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .city(userForm.getCity())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .build();

        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    @Override
    public User getUserByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    @Override
    public City getUserCityById(Long userId){
        return userRepository.getCityByUserId(userId);
    }

}
