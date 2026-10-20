package com.sanchez.bankingmongo.infrastructure;

import com.sanchez.cqrs.core.commands.BaseCommand;
import com.sanchez.cqrs.core.commands.interfaces.CommandHandlerMethod;
import com.sanchez.cqrs.core.infrastructure.interfaces.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispacher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> commandHandlerMethod) {

        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(commandHandlerMethod);
    }

    @Override
    public void send(BaseCommand baseCommand) {

        var handlers = routes.get(baseCommand.getClass());
        if (handlers == null) {
            throw  new RuntimeException("El base command handlers no fue registrado ");
        }
        if (!handlers.isEmpty()) {
            throw  new RuntimeException("No se puede enviar un base command que tiene mas de un handlers ");
        }

        handlers.get(0).handle(baseCommand);

    }
}
