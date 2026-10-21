package com.sanchez.bankingmongo.api.interfaces;

import com.sanchez.bankingmongo.api.command.CloseAccountCommand;
import com.sanchez.bankingmongo.api.command.DeposiFundsCommand;
import com.sanchez.bankingmongo.api.command.OpenAccountCommand;
import com.sanchez.bankingmongo.api.command.WithDrawFundsCommand;

public interface CommandHandler {

    void handle(OpenAccountCommand openAccountCommand);
    void handle(DeposiFundsCommand deposiFundsCommand);
    void handle(WithDrawFundsCommand withDrawFundsCommand);
    void handle(CloseAccountCommand closeAccountCommand);

}
