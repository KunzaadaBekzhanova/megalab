package megalab.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import megalab.exceptions.NotFoundException;
import megalab.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        return userRepo.findByNickname(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with nickname = %s not found", username)));
    }
}
