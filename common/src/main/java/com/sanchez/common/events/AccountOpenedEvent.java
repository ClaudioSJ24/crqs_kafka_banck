package com.sanchez.common.events;

import com.sanchez.common.dto.AccountType;
import com.sanchez.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;


public class AccountOpenedEvent extends BaseEvent {

    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;

    private AccountOpenedEvent() {
    }

    public String getAccountHolder() { return accountHolder; }
    public AccountType getAccountType() { return accountType; }
    public Date getCreatedDate() { return createdDate; }
    public double getOpeningBalance() { return openingBalance; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseEvent.Builder<AccountOpenedEvent, Builder> {

        private Builder() {
            super(new AccountOpenedEvent());
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder accountHolder(String accountHolder) {
            message.accountHolder = accountHolder;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            message.accountType = accountType;
            return this;
        }

        public Builder createdDate(Date createdDate) {
            message.createdDate = createdDate;
            return this;
        }

        public Builder openingBalance(double openingBalance) {
            message.openingBalance = openingBalance;
            return this;
        }
    }


}
