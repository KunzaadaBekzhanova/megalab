package megalab.services;

import lombok.RequiredArgsConstructor;
import megalab.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
}
