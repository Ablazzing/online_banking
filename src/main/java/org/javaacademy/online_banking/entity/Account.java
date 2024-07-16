package org.javaacademy.online_banking.entity;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Account {
    @NonNull
    private final String number;
    @NonNull
    private final User user;
    private BigDecimal money = BigDecimal.ZERO;

    public void addMoney(BigDecimal money) {
        money = money.add(money);
    }

    public void minusMoney(BigDecimal money) {
        BigDecimal result = money.min(money);
        if (result.compareTo(BigDecimal.ZERO) == -1) {
            throw new RuntimeException("Денег меньше нуля");
        }
    }
}
