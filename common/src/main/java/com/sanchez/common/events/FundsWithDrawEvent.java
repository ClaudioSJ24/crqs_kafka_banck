package com.sanchez.common.events;

import com.sanchez.cqrs.core.events.BaseEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FundsWithDrawEvent extends BaseEvent {

    private double amount;

    private FundsWithDrawEvent() {
    }

    public double getAmount() { return amount; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseEvent.Builder<FundsWithDrawEvent, Builder> {

        private Builder() {
            super(new FundsWithDrawEvent());
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
