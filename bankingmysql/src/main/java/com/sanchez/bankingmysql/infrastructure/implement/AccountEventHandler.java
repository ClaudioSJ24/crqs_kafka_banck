package com.sanchez.bankingmysql.infrastructure.implement;

import com.sanchez.bankingmysql.domain.BankAccount;
import com.sanchez.bankingmysql.domain.interfaces.AccountRepository;
import com.sanchez.bankingmysql.infrastructure.handlers.EventHandler;
import com.sanchez.common.events.AccountClosedEvent;
import com.sanchez.common.events.AccountOpenedEvent;
import com.sanchez.common.events.FundsDepositedEvent;
import com.sanchez.common.events.FundsWithDrawEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void on(AccountOpenedEvent accountOpenedEvent) {
        var bankAccount = BankAccount.builder()
                .id(accountOpenedEvent.getId())
                .accountHolder(accountOpenedEvent.getAccountHolder())
                .createdDate(accountOpenedEvent.getCreatedDate())
                .accountType(accountOpenedEvent.getAccountType())
                .balance(accountOpenedEvent.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent fundsDepositedEvent) {

        var bankAccount = accountRepository.findById(fundsDepositedEvent.getId());

        if (bankAccount.isEmpty()) {

            return;

        }

        var currentBalance = bankAccount.get().getBalance();
        var lastBalance = currentBalance + fundsDepositedEvent.getAmount();
        bankAccount.get().setBalance(lastBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithDrawEvent fundsWithDrawEvent) {

        var bankAccount = accountRepository.findById(fundsWithDrawEvent.getId());

        if (bankAccount.isEmpty()) {

            return;

        }
        var currentBalance = bankAccount.get().getBalance();
        var lastBalance = currentBalance - fundsWithDrawEvent.getAmount();
        bankAccount.get().setBalance(lastBalance);
        accountRepository.save(bankAccount.get());

    }

    @Override
    public void on(AccountClosedEvent accountClosedEvent) {

        accountRepository.deleteById(accountClosedEvent.getId());
    }
}
