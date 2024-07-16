package org.javaacademy.online_banking.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.online_banking.entity.Account;
import org.javaacademy.online_banking.entity.FinancialOperation;
import org.javaacademy.online_banking.entity.OperationType;
import org.javaacademy.online_banking.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.javaacademy.online_banking.entity.OperationType.PAY;
import static org.javaacademy.online_banking.entity.OperationType.RECEIVE;

@Service
@RequiredArgsConstructor
public class BankService {
    private final AccountService accountService;
    private final UserService userService;
    private final FinanceOperationService financeOperationService;

    public void pay(String number, BigDecimal amount, String descr, String token) {
        User user = userService.findUserByToken(token);
        if (!accountService.isAccountOwner(number, user)) {
            throw new RuntimeException("User is not owner this account");
        }
        accountService.pay(number, amount);
        financeOperationService.add(number, now(), PAY, amount, descr);
    }

    public List<FinancialOperation> findOperationsByUser(String token) {
        User user = userService.findUserByToken(token);
        return financeOperationService.findOperationsByUser(user);
    }

    public void receive(String number, BigDecimal amount, String descr) {
        accountService.receive(number, amount);
        financeOperationService.add(number, now(), RECEIVE, amount, descr);
    }
}
