package com.sanchez.bankingmysql.infrastructure.implement;

import com.sanchez.cqrs.core.infrastructure.interfaces.QueryDispatcher;
import com.sanchez.cqrs.core.querys.BaseQuery;
import com.sanchez.cqrs.core.querys.interfaces.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();
    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {

        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);

    }

    @Override
    public <U> List<U> send(BaseQuery baseQuery) {

        var handlers = routes.get(baseQuery.getClass());

        if (handlers == null || handlers.size() <= 0) {

            throw new RuntimeException("Ningun query handler fue registrado para este objeto query");
        }

        if (handlers.size() > 1) {

            throw new RuntimeException("No puede enviar un query que tenga mas de dos handlers");

        }
        return handlers.getFirst().handle(baseQuery);
    }
}
