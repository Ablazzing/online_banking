package org.javaacademy.online_banking.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.online_banking.entity.Account;
import org.javaacademy.online_banking.entity.User;
import org.javaacademy.online_banking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static int countAccounts = 0;
    private final AccountRepository accountRepository;

    public String createAccount(User user) {
        Account account = new Account(generateNumber(), user);
        accountRepository.add(account);
        return account.getNumber();
    }

    public void receive(String number, BigDecimal amount) {
        Account account = accountRepository.findByNumber(number).orElseThrow();
        account.addMoney(amount);
    }

    public List<Account> getUserAccounts(User user) {
        return accountRepository.findAccountsByUser(user);
    }

    public BigDecimal getAccountCurrentAmount(String number) {
        return accountRepository.findByNumber(number).orElseThrow().getMoney();
    }

    public void pay(String number, BigDecimal amount) {
        Account account = accountRepository.findByNumber(number).orElseThrow();
        account.minusMoney(amount);
    }

    public boolean isAccountOwner(String number, User user) {
        Account account = accountRepository.findByNumber(number).orElseThrow();
        return isAccountOwner(account, user);
    }

    public boolean isAccountOwner(Account account, User user) {
        return Objects.equals(user, account.getUser());
    }

    public Account findAccountByNumber(String number) {
        return accountRepository.findByNumber(number).orElseThrow();
    }

    private String generateNumber() {
        countAccounts++;
        return "0".repeat(6 - String.valueOf(countAccounts).length()) + countAccounts;
    }
}
