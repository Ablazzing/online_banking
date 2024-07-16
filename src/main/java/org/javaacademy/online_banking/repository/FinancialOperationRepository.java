package org.javaacademy.online_banking.repository;

import org.javaacademy.online_banking.entity.Account;
import org.javaacademy.online_banking.entity.FinancialOperation;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
public class FinancialOperationRepository {
    private Map<UUID, FinancialOperation> operationMap = new HashMap<>();

    public void add(FinancialOperation operation) {
        operationMap.put(operation.getUuid(), operation);
    }

    public List<FinancialOperation> findAll() {
        return operationMap.values().stream().toList();
    }

    public List<FinancialOperation> findByAccountNumber(String number) {
        return operationMap.values().stream().filter(e -> Objects.equals(number, e.getNumber())).toList();
    }

    public List<FinancialOperation> findByAccounts(List<Account> accounts) {
        List<String> numbers = accounts.stream().map(Account::getNumber).toList();
        return operationMap.values().stream()
                .filter(e -> numbers.contains(e.getNumber()))
                .toList();
    }
}
