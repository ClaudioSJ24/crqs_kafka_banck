package com.sanchez.bankingmysql.infrastructure.consumers.implement;

import com.sanchez.bankingmysql.infrastructure.consumers.interfaces.EventConsumer;
import com.sanchez.bankingmysql.infrastructure.handlers.EventHandler;
import com.sanchez.common.events.AccountClosedEvent;
import com.sanchez.common.events.AccountOpenedEvent;
import com.sanchez.common.events.FundsDepositedEvent;
import com.sanchez.common.events.FundsWithDrawEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountOpenedEvent accountOpenedEvent, Acknowledgment acknowledgment) {

        eventHandler.on(accountOpenedEvent);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundsDepositedEvent fundsDepositedEvent, Acknowledgment acknowledgment) {

        eventHandler.on(fundsDepositedEvent);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "FundsWithDrawEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundsWithDrawEvent fundsWithDrawEvent, Acknowledgment acknowledgment) {

        eventHandler.on(fundsWithDrawEvent);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountClosedEvent accountClosedEvent, Acknowledgment acknowledgment) {

        eventHandler.on(accountClosedEvent);
        acknowledgment.acknowledge();
    }
}
