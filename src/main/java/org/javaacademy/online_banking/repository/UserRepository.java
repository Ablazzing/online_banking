package org.javaacademy.online_banking.repository;

import org.javaacademy.online_banking.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    private final Map<UUID, User> usersMap = new HashMap<>();

    public void add(User user) {
        usersMap.put(user.getUid(), user);
    }

    public Optional<User> get(UUID userUid) {
        return Optional.ofNullable(usersMap.get(userUid));
    }


    public Optional<User> findByPhone(String phone) {
        return usersMap.values().stream().filter(e -> e.getPhone().equals(phone)).findFirst();
    }
}
