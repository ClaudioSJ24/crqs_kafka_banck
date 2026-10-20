package com.sanchez.common.events;

import com.sanchez.cqrs.core.events.BaseEvent;

public class AccountClosedEvent extends BaseEvent {

    public AccountClosedEvent(int version) {
        super(version);
    }

}
