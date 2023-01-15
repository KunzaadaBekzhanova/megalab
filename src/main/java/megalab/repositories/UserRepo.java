package megalab.repositories;

import megalab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    // It's Appendix, the JpaRepository can write query itself
    User findByNickname(String username);
}
