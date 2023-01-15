package megalab.services;

import lombok.RequiredArgsConstructor;
import megalab.dtos.UserDTO;
import megalab.dtos.requests.CreateUserRequest;
import megalab.dtos.responses.AuthResponse;
import megalab.exceptions.BadRequestException;
import megalab.models.User;
import megalab.repositories.UserRepo;
import megalab.security.TokenUtilities;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtilities tokenUtilities;
    public AuthResponse save(CreateUserRequest createUserRequest) {
        if (userRepo.existsByNickname(createUserRequest.nickname())) {
            throw new BadRequestException(
                    String.format("Nickname = %s already in use", createUserRequest.nickname())
            );
        }
        User user = userRepo.save(convert(createUserRequest));
        return AuthResponse.builder()
                .token(tokenUtilities.generateToken(user.getNickname()))
                .user(new UserDTO(user))
                .build();
    }

    private User convert(CreateUserRequest createUserRequest) {
        return User.builder()
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .nickname(createUserRequest.nickname())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .build();
    }
}
