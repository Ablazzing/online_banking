package org.javaacademy.online_banking.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.online_banking.entity.Account;
import org.javaacademy.online_banking.entity.FinancialOperation;
import org.javaacademy.online_banking.entity.OperationType;
import org.javaacademy.online_banking.entity.User;
import org.javaacademy.online_banking.repository.FinancialOperationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinanceOperationService {
    private final FinancialOperationRepository operationRepository;
    private final AccountService accountService;

    public List<FinancialOperation> findAll() {
        return operationRepository.findAll();
    }

    public void add(String number,
                    LocalDateTime dateTime,
                    OperationType operationType,
                    BigDecimal amount,
                    String descr) {
        FinancialOperation financialOperation = new FinancialOperation(
                UUID.randomUUID(),
                dateTime,
                number,
                operationType,
                amount,
                descr
        );
        operationRepository.add(financialOperation);
    }

    public List<FinancialOperation> findOperationsByUser(User user) {
        List<Account> accounts = accountService.getUserAccounts(user);
        return operationRepository.findByAccounts(accounts);
    }
}
