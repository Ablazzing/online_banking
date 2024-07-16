package org.javaacademy.online_banking.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.online_banking.entity.User;
import org.javaacademy.online_banking.repository.AuthRepository;
import org.javaacademy.online_banking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String TOKEN_PREFIX = "online";
    private static final String TOKEN_POSTFIX = "online";
    private final UserRepository userRepository;
    private final AuthService authService;
    private Random random = new Random();

    public String registerUser(String phone, String fullName) {
        User user = new User(phone, fullName);
        userRepository.add(user);
        String pincode = pinGenerator();
        authService.createPair(user, pincode);
        return pincode;
    }

    public String authenticate(String phone, String pincode) {
        User user = userRepository.findByPhone(phone).orElseThrow();
        authService.auth(user, pincode);
        return createToken(user);
    }

    public User findUserByToken(String token) {
        String userIdText = token.substring(
                token.indexOf(TOKEN_PREFIX) + TOKEN_PREFIX.length(),
                token.indexOf(TOKEN_POSTFIX) + TOKEN_POSTFIX.length());
        return userRepository.get(UUID.fromString(userIdText)).orElseThrow();
    }

    private String createToken(User user) {
        return TOKEN_PREFIX + user.getUid() + TOKEN_POSTFIX;
    }

    public User getUser(String phone) {
        return userRepository.findByPhone(phone).orElseThrow();
    }

    private String pinGenerator() {
        return IntStream.range(0, 4).mapToObj(e -> String.valueOf(random.nextInt(10))).collect(Collectors.joining());
    }
}
