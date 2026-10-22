package com.sanchez.bankingmongo.api.command;

import com.sanchez.cqrs.core.commands.BaseCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeposiFundsCommand extends BaseCommand {

    private double amount;

    public DeposiFundsCommand(String id) {
        super(id);
    }

    public double getAmount() { return amount; }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends BaseCommand.Builder<DeposiFundsCommand, Builder> {

        private Builder(String id) {
            super(new DeposiFundsCommand(id));
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
