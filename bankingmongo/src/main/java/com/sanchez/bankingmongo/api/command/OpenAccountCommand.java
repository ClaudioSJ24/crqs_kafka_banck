package com.sanchez.bankingmongo.api.command;

import com.sanchez.common.dto.AccountType;
import com.sanchez.cqrs.core.commands.BaseCommand;

public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;

    public OpenAccountCommand(String id) {
        super(id);
    }



    public String getAccountHolder() { return accountHolder; }
    public AccountType getAccountType() { return accountType; }
    public double getOpeningBalance() { return openingBalance; }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends BaseCommand.Builder<OpenAccountCommand, Builder> {

        private Builder(String id) {
            super(new OpenAccountCommand(id));
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

        public Builder openingBalance(double openingBalance) {
            message.openingBalance = openingBalance;
            return this;
        }
    }

}
