package com.master.transaction.account.application;

import com.master.transaction.account.domain.Account;
import com.master.transaction.account.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // 예외 발생 시 자동 롤백
        if (amount > fromAccount.getBalance()) {
            throw new RuntimeException("Insufficient funds");
        }

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Transactional(rollbackFor = CheckedException.class)
    public void transferWithCheckedException(Long fromAccountId, Long toAccountId, Double amount) throws CheckedException {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        if (amount > fromAccount.getBalance()) {
            throw new CheckedException("Insufficient funds for transfer");
        }

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
