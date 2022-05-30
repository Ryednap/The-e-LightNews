package com.example.newsapp.user;

import com.example.newsapp.auth.AuthRegisterTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Pair<Integer, String> registerUser(AuthRegisterTemplate registerTemplate) {
        AppUser user = new AppUser(
                registerTemplate.getUsername(),
                registerTemplate.getEmail(),
                registerTemplate.getPassword(),
                LocalDateTime.now(),
                Role.USER
        );
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException integrityViolationException) {
            log.error("INTEGRITY VIOLATION OCCURRED WHILE SAVING USER: " + integrityViolationException.getMessage());
            return Pair.of(0, "User with similar username or email already exists.");
        }
        return Pair.of(1, "User Registered Success. Please Login to continue");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAppUserByUsername(username)
                .orElseThrow(() -> {
                    log.error("User Not found");
                    return new UsernameNotFoundException("No such user with username: " + username + " exists");
                });

    }
}
