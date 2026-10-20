package com.sanchez.common.events;

import com.sanchez.cqrs.core.events.BaseEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FoudsDepositedEvent extends BaseEvent {

    private double amount;

    public FoudsDepositedEvent(int version) {
        super(version);
    }

    public FoudsDepositedEvent(int version, double amount) {
        super(version);
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
