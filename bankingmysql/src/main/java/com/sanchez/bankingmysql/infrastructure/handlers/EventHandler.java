package com.sanchez.bankingmysql.infrastructure.handlers;

import com.sanchez.common.events.AccountClosedEvent;
import com.sanchez.common.events.AccountOpenedEvent;
import com.sanchez.common.events.FundsDepositedEvent;
import com.sanchez.common.events.FundsWithDrawEvent;

public interface EventHandler {

    void on(AccountOpenedEvent accountOpenedEvent);
    void on(FundsDepositedEvent fundsDepositedEvent);
    void on(FundsWithDrawEvent fundsWithDrawEvent);
    void on(AccountClosedEvent accountClosedEvent);
}
