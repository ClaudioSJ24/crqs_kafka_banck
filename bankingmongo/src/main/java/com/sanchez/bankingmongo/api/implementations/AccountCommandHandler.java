package com.sanchez.bankingmongo.api.implementations;

import com.sanchez.bankingmongo.api.command.CloseAccountCommand;
import com.sanchez.bankingmongo.api.command.DeposiFundsCommand;
import com.sanchez.bankingmongo.api.command.OpenAccountCommand;
import com.sanchez.bankingmongo.api.command.WithDrawFundsCommand;
import com.sanchez.bankingmongo.api.interfaces.CommandHandler;
import com.sanchez.bankingmongo.domain.AccountAggregate;
import com.sanchez.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand openAccountCommand) {

        var aggregate = new AccountAggregate(openAccountCommand);
        eventSourcingHandler.save(aggregate);

    }

    @Override
    public void handle(DeposiFundsCommand deposiFundsCommand) {

        var aggregate = eventSourcingHandler.getById(deposiFundsCommand.getId());
        aggregate.depositFunds(deposiFundsCommand.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(WithDrawFundsCommand withDrawFundsCommand) {

        var  aggregate = eventSourcingHandler.getById(withDrawFundsCommand.getId());
        if (withDrawFundsCommand.getAmount() > aggregate.getBalance()) {

            throw new IllegalStateException("Fondos Insuficientes, no se puede retirar dinero");

        }

        aggregate.withdrawFunds(withDrawFundsCommand.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand closeAccountCommand) {

        var aggregate = eventSourcingHandler.getById(closeAccountCommand.getId());
        aggregate.closeAccount();
        eventSourcingHandler.save(aggregate);

    }
}
