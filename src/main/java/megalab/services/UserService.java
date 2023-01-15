package megalab.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import megalab.dtos.UserDTO;
import megalab.dtos.requests.CreateUserRequest;
import megalab.dtos.responses.AuthResponse;
import megalab.dtos.responses.GetProfileResponse;
import megalab.enums.Role;
import megalab.exceptions.BadRequestException;
import megalab.models.User;
import megalab.repositories.CustomNewsRepo;
import megalab.repositories.NewsRepo;
import megalab.repositories.UserRepo;
import megalab.security.TokenUtilities;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final NewsRepo newsRepo;
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
                .role(Role.USER)
                .build();
    }

    public GetProfileResponse getProfile(Authentication auth) {
        return GetProfileResponse.builder()
                .userInfo(userRepo.findByNicknameAndCast(auth.getName()))
                .news(newsRepo.findAllByUserNickname(auth.getName()))
                .build();
    }

    @Transactional
    public UserDTO updateUser(Authentication auth, UserDTO userRequest) {
        User user = userRepo.getByNickname(auth.getName());
        changeUser(user, userRequest);
        if (!user.getNickname().equalsIgnoreCase(userRequest.getNickname()) && StringUtils.hasText(userRequest.getNickname())) {
            if (userRepo.existsByNickname(userRequest.getNickname())) {
                throw new BadRequestException(String.format("Nickname = %s already in use", userRequest.getNickname()));
            }
            user.setNickname(userRequest.getNickname());
        }
        return new UserDTO(user);
    }

    private static void changeUser(User user, UserDTO userRequest) {
        user.setImage(userRequest.getImage());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
    }
}
