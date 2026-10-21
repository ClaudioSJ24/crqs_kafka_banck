package com.sanchez.bankingmongo.domain;

import com.sanchez.bankingmongo.api.command.OpenAccountCommand;
import com.sanchez.common.events.AccountClosedEvent;
import com.sanchez.common.events.AccountOpenedEvent;
import com.sanchez.common.events.FundsDepositedEvent;
import com.sanchez.common.events.FundsWithDrawEvent;
import com.sanchez.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;


public class AccountAggregate extends AggregateRoot {

    private Boolean active;
    private double balance;

    public AccountAggregate() {
    }

    public AccountAggregate(OpenAccountCommand openAccountCommand){

        raiseEvent(AccountOpenedEvent.builder()
                .id(openAccountCommand.getId())
                .accountHolder(openAccountCommand.getAccountHolder())
                .createdDate(new Date())
                .accountType(openAccountCommand.getAccountType())
                .openingBalance(openAccountCommand.getOpeningBalance())
                .build());
    }

    public  void apply(AccountOpenedEvent accountOpenedEvent){
        this.id = accountOpenedEvent.getId();
        this.active = true;
        this.balance = accountOpenedEvent.getOpeningBalance();
    }

    public void depositFunds(double amount){

        if (!this.active) {
            throw new IllegalStateException("Los fondos no pueden ser depositados en esta cuenta");
        }

        if (amount <= 0) {
            throw new IllegalStateException("Los depositos no pueden ser igual o menor que cero");
        }

        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsDepositedEvent fundsDepositedEvent){
        this.id = fundsDepositedEvent.getId();
        this.balance += fundsDepositedEvent.getAmount();
    }

    public void withdrawFunds(double amount){
        if (!this.active) {
            throw new IllegalStateException("La cuenta bancaria esta cerrada");
        }

        raiseEvent(FundsWithDrawEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsWithDrawEvent fundsWithDrawEvent){
        this.id = fundsWithDrawEvent.getId();
        this.balance -= fundsWithDrawEvent.getAmount();
    }

    public void closeAccount(){

        if (!active) {
            throw new IllegalStateException("La cuenta de banco esta cerrada");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent accountClosedEvent){
        this.id = accountClosedEvent.getId();
        this.active = false;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
