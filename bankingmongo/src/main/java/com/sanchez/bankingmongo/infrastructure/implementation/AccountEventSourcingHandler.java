package com.sanchez.bankingmongo.infrastructure.implementation;

import com.sanchez.bankingmongo.domain.AccountAggregate;
import com.sanchez.cqrs.core.domain.AggregateRoot;
import com.sanchez.cqrs.core.handlers.EventSourcingHandler;
import com.sanchez.cqrs.core.infrastructure.interfaces.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;
    @Override
    public void save(AggregateRoot aggregateRoot) {

        eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommitedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();

    }

    @Override
    public AccountAggregate getById(String id) {

        var aggregate = new AccountAggregate();
        var events = eventStore.getEvent(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var lastVersion = events.stream().map(
                    x -> x.getVersion()
            ).max(
                    Comparator.naturalOrder()
            );

            aggregate.setVersion(lastVersion.get());
        }
        return aggregate;
    }
}
