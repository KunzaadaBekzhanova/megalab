package megalab.apis;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import megalab.dtos.requests.AuthRequest;
import megalab.dtos.requests.CreateUserRequest;
import megalab.dtos.responses.AuthResponse;
import megalab.services.AuthService;
import megalab.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@PermitAll
public class AuthAPI {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/authenticate")
    AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    AuthResponse register(@RequestBody CreateUserRequest createUserRequest) {
        return userService.save(createUserRequest);
    }
}
