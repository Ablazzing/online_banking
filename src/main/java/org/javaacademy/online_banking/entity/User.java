package org.javaacademy.online_banking.entity;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class User {
    @NonNull
    private String phone;
    @NonNull
    private String fullName;
    private UUID uid = UUID.randomUUID();
}
