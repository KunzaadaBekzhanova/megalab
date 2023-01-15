package megalab.security;

import lombok.SneakyThrows;
import megalab.repositories.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {

    @Bean
    @SneakyThrows
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        return httpSecurity.csrf().and().cors().disable()
                .authorizeHttpRequests(authz -> {
                    authz.anyRequest().permitAll();
                }).build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepo userRepo) {
        return username -> userRepo.findByNickname(username);
    }
}
