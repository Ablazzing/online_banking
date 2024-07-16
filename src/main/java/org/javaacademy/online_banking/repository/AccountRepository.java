package org.javaacademy.online_banking.repository;

import org.javaacademy.online_banking.entity.Account;
import org.javaacademy.online_banking.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {
    private Map<String, Account> accountMap = new HashMap<>();

    public void add(Account account) {
        accountMap.put(account.getNumber(), account);
    }

    public Optional<Account> findByNumber(String number) {
        return Optional.ofNullable(accountMap.get(number));
    }

    public List<Account> findAccountsByUser(User user) {
        return accountMap.values().stream().filter(e -> e.getUser().equals(user)).toList();
    }
}
