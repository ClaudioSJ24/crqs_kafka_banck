package com.sanchez.bankingmysql.infrastructure.consumers.interfaces;

import com.sanchez.common.events.AccountClosedEvent;
import com.sanchez.common.events.AccountOpenedEvent;
import com.sanchez.common.events.FundsDepositedEvent;
import com.sanchez.common.events.FundsWithDrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

    void consume(@Payload AccountOpenedEvent accountOpenedEvent, Acknowledgment acknowledgment);
    void consume(@Payload FundsDepositedEvent fundsDepositedEvent , Acknowledgment acknowledgment);
    void consume(@Payload FundsWithDrawEvent fundsWithDrawEvent, Acknowledgment acknowledgment);
    void consume(@Payload AccountClosedEvent accountClosedEvent, Acknowledgment acknowledgment);
}
