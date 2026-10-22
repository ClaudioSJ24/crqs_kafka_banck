package com.sanchez.bankingmongo.infrastructure.implementation;

import com.sanchez.cqrs.core.events.BaseEvent;
import com.sanchez.cqrs.core.producer.interfaces.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public void produce(String topic, BaseEvent baseEvent) {

        this.kafkaTemplate.send(topic, baseEvent);

    }
}
