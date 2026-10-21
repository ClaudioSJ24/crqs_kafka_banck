package com.sanchez.common.events;

import com.sanchez.cqrs.core.events.BaseEvent;

public class FundsDepositedEvent extends BaseEvent {

    private double amount;

    private FundsDepositedEvent() {
    }

    public double getAmount() { return amount; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseEvent.Builder<FundsDepositedEvent, Builder> {

        private Builder() {
            super(new FundsDepositedEvent());
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
