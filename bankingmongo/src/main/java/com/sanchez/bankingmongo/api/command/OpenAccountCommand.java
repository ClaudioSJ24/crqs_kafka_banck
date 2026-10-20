package com.sanchez.bankingmongo.api.command;

import com.sanchez.common.dto.AccountType;
import com.sanchez.cqrs.core.commands.BaseCommand;

public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;

    public OpenAccountCommand(String id, String accountHolder, AccountType accountType, double openingBalance) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
    }


}
