package org.example.service;

import org.example.model.User;
import org.example.model.Role;
import org.example.repository.UserRepository;
import org.example.security.JWTUtils;
import org.example.security.PasswordConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public UserService(UserRepository userRepository,
                       PasswordConfig passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager,
                       JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь - null");
        }
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        if (user.getRole() == null) user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public String login(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
        );

        var foundUser = userRepository.getUserByName(user.getName())
                .orElseThrow();

        return jwtUtils.generateToken(foundUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
    }
}