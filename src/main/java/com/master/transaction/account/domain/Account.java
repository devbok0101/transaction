package com.master.transaction.account.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double balance;

    public Account(Long id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance =balance;
    }
}