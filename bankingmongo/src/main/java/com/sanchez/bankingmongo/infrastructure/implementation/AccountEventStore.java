package com.sanchez.bankingmongo.infrastructure.implementation;

import com.sanchez.bankingmongo.domain.AccountAggregate;
import com.sanchez.bankingmongo.domain.interfaces.EventStoreRepository;
import com.sanchez.cqrs.core.events.BaseEvent;
import com.sanchez.cqrs.core.events.EventModel;
import com.sanchez.cqrs.core.exceptions.AggregateNotFoundException;
import com.sanchez.cqrs.core.exceptions.CurrencyException;
import com.sanchez.cqrs.core.infrastructure.interfaces.EventStore;
import com.sanchez.cqrs.core.producer.interfaces.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> baseEventIterable, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {

            throw  new CurrencyException();

        }

        var version = expectedVersion;

        for (var event: baseEventIterable){
            version++;
            event.setVersion(version);

            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .baseEvent(event)
                    .build();

            var persistedEvent = eventStoreRepository.save(eventModel);

            //SE ENVIA EL EVENTO A KAFKA
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);

            }
        }
    }

    @Override
    public List<BaseEvent> getEvent(String aggregateId) {

        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("La cuenta de banco es incorrecta");
        }

        return eventStream.stream().map(EventModel::getBaseEvent).collect(Collectors.toList());
    }
}
