package org.javaacademy.online_banking.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class AuthRepository {
    private Map<UUID, String> uidPinPairs = new HashMap<>();

    public void add(UUID userUid, String pincode) {
        uidPinPairs.put(userUid, pincode);
    }

    public boolean authenticate(UUID userUid, String pincode) {
        return uidPinPairs.get(userUid).equals(pincode);
    }
}
