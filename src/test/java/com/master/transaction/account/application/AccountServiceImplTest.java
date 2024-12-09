package com.master.transaction.account.application;

import com.master.transaction.account.application.AccountService;
import com.master.transaction.account.application.CheckedException;
import com.master.transaction.account.domain.Account;
import com.master.transaction.account.infrastructure.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("RunTimeException은 rollbackfor의 대상이므로, 롤백이 되어야한다.")
    void testRollbackForRuntimeException() {
        // Given: 초기 데이터 설정
        Long fromAccountId = 1L;
        Long toAccountId = 2L;
        Double initialBalanceFrom = 1000.0;
        Double initialBalanceTo = 500.0;

        accountRepository.save(new Account(initialBalanceFrom));
        accountRepository.save(new Account(initialBalanceTo));

        // When & Then: 런타임 예외가 발생하고 롤백되는지 확인
        assertThrows(RuntimeException.class, () -> {
            accountService.transfer(fromAccountId, toAccountId, 1500.0); // 예외 발생
        });

        // 롤백 확인: 데이터베이스 상태가 초기 상태와 동일해야 함
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();
        assertEquals(initialBalanceFrom, fromAccount.getBalance());
        assertEquals(initialBalanceTo, toAccount.getBalance());
    }

    @Test
    @DisplayName("CheckedException rollbackfor의 대상이므로, 롤백이 되어야한다.")
    void testRollbackForCheckedException() {
        // Given: 초기 데이터 설정
        Long fromAccountId = 1L;
        Long toAccountId = 2L;
        Double initialBalanceFrom = 1000.0;
        Double initialBalanceTo = 500.0;

        accountRepository.save(new Account(initialBalanceFrom));
        accountRepository.save(new Account(initialBalanceTo));

        // When & Then: 체크 예외 발생 시 롤백 설정
        assertThrows(CheckedException.class, () -> {
            accountService.transferWithCheckedException(fromAccountId, toAccountId, 1500.0);
        });

        // 롤백 확인
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();
        assertEquals(initialBalanceFrom, fromAccount.getBalance());
        assertEquals(initialBalanceTo, toAccount.getBalance());
    }
}