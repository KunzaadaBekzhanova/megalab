package megalab.apis;

import lombok.RequiredArgsConstructor;
import megalab.dtos.UserDTO;
import megalab.dtos.responses.GetProfileResponse;
import megalab.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @GetMapping("/profile")
    GetProfileResponse getUserProfile(Authentication auth) {
        return userService.getProfile(auth);
    }

    @PutMapping
    UserDTO updateUser(Authentication auth, @RequestBody UserDTO userRequest) {
        return userService.updateUser(auth, userRequest);
    }
}
