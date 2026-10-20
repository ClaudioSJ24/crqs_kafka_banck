package com.sanchez.common.events;

import com.sanchez.common.dto.AccountType;
import com.sanchez.cqrs.core.events.BaseEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class AccountOpenedEvent extends BaseEvent {

    private  String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private  double openingBalance;

    public AccountOpenedEvent(int version) {
        super(version);
    }

    public AccountOpenedEvent(int version,
                              String accountHolder,
                              AccountType accountType,
                              Date createdDate,
                              double openingBalance) {
        super(version);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.createdDate = createdDate;
        this.openingBalance = openingBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accountHolder", accountHolder)
                .append("accountType", accountType)
                .append("createdDate", createdDate)
                .append("openingBalance", openingBalance)
                .toString();
    }
}
