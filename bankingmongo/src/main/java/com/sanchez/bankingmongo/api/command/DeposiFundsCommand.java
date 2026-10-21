package com.sanchez.bankingmongo.api.command;

import com.sanchez.cqrs.core.commands.BaseCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeposiFundsCommand extends BaseCommand {

    private double amount;

    private DeposiFundsCommand() {
    }

    public double getAmount() { return amount; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseCommand.Builder<DeposiFundsCommand, Builder> {

        private Builder() {
            super(new DeposiFundsCommand());
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder amount(double amount) {
            message.amount = amount;
            return this;
        }
    }


}
