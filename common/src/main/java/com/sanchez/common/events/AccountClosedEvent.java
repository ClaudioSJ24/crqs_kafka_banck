package com.sanchez.common.events;

import com.sanchez.cqrs.core.events.BaseEvent;


public class AccountClosedEvent extends BaseEvent {

    private  String id;

    public AccountClosedEvent() {

    }



    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseEvent.Builder<AccountClosedEvent, Builder> {

        private Builder() {
            super(new AccountClosedEvent());
        }

        @Override
        protected Builder self() {
            return this;
        }

        // Mantiene sincronizados el id de Message y el id propio de esta clase
        @Override
        public Builder id(String id) {
            super.id(id);
            message.id = id;
            return this;
        }
    }

}
