package org.javaacademy.online_banking.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.online_banking.entity.User;
import org.javaacademy.online_banking.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void auth(User user, String pincode) {
        if (!authRepository.authenticate(user.getUid(), pincode)) {
            throw new RuntimeException("Auth exception");
        }
    }

    public void createPair(User user, String pincode) {
        authRepository.add(user.getUid(), pincode);
    }
}
