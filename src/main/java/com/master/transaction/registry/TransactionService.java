package com.master.transaction.registry;


import jakarta.annotation.Resource;
import jakarta.transaction.Synchronization;
import jakarta.transaction.TransactionSynchronizationRegistry;
import lombok.extern.slf4j.Slf4j;

import static jakarta.transaction.Status.STATUS_COMMITTED;

@Slf4j
public class TransactionService {

    @Resource
    private TransactionSynchronizationRegistry registry;


    public void performTransaction() {
        Object transactionKey = registry.getTransactionKey();
        log.info("Transaction Key : {}", transactionKey);

        registry.registerInterposedSynchronization(new Synchronization() {
            @Override
            public void beforeCompletion() {
                log.info("before transaction commit");
            }

            @Override
            public void afterCompletion(int status) {
                if (status == STATUS_COMMITTED) {
                    log.info("transaction committed");
                } else {
                    log.info("transaction roll back");
                }
            }
        });
    }
}
