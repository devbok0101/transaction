package com.master.transaction.account.application;

import java.io.IOException;

public interface AccountService {
    public void transfer(Long fromAccountId, Long toAccountId, Double amount);
    public void transferWithCheckedException(Long fromAccountId, Long toAccountId, Double amount) throws IOException, CheckedException;
}
