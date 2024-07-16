package org.javaacademy.online_banking.entity;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FinancialOperation {
    private @NonNull UUID uuid;
    private @NonNull LocalDateTime dateTime;
    private @NonNull String number;
    private @NonNull OperationType operationType;
    private @NonNull BigDecimal amount;
    private @NonNull String descr;
}
