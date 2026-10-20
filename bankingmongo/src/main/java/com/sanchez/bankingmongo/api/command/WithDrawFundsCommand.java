package com.sanchez.bankingmongo.api.command;

import com.sanchez.cqrs.core.commands.BaseCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WithDrawFundsCommand extends BaseCommand {

    private double amount;

    public WithDrawFundsCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("amount", amount)
                .toString();
    }
}
