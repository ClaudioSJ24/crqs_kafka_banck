package com.sanchez.bankingmysql.domain;

import com.sanchez.common.dto.AccountType;
import com.sanchez.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Id;

import javax.xml.crypto.Data;
import java.util.Date;


public class BankAccount extends BaseEntity {

    @Id
    private String id;
    private String accountHolder;
    private Date createdDate;
    private AccountType accountType;
    private double balance;

    private BankAccount() {
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BankAccount(String id, String accountHolder, Date createdDate, AccountType accountType, double balance) {
        this.id = id;
        this.accountHolder = accountHolder;
        this.createdDate = createdDate;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getId() { return id; }
    public String getAccountHolder() { return accountHolder; }
    public Date getCreatedDate() { return createdDate; }
    public AccountType getAccountType() { return accountType; }
    public double getBalance() { return balance; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final BankAccount account = new BankAccount();

        private Builder() {
        }

        public Builder id(String id) {
            account.id = id;
            return this;
        }

        public Builder accountHolder(String accountHolder) {
            account.accountHolder = accountHolder;
            return this;
        }

        public Builder createdDate(Date createdDate) {
            account.createdDate = createdDate;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            account.accountType = accountType;
            return this;
        }

        public Builder balance(double balance) {
            account.balance = balance;
            return this;
        }

        public BankAccount build() {
            return account;
        }
    }

}
