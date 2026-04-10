package ru.technocracy.echoserver.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.repositories.UserRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

        User user = userRepository.findByNickname(nickname);
        if(user == null){throw new UsernameNotFoundException("Пользователь не найден");}

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getNickname())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
