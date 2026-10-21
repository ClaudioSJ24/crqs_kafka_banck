package com.sanchez.bankingmongo.domain.interfaces;

import com.sanchez.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel, String> {

    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
