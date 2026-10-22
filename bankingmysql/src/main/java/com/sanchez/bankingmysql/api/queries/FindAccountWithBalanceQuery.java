package com.sanchez.bankingmysql.api.queries;

import com.sanchez.bankingmysql.api.dto.EqualityType;
import com.sanchez.cqrs.core.querys.BaseQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FindAccountWithBalanceQuery extends BaseQuery {

    private double balance;
    private EqualityType equalityType;

    public FindAccountWithBalanceQuery(double balance, EqualityType equalityType) {
        this.balance = balance;
        this.equalityType = equalityType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public EqualityType getEqualityType() {
        return equalityType;
    }

    public void setEqualityType(EqualityType equalityType) {
        this.equalityType = equalityType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("balance", balance)
                .append("equalityType", equalityType)
                .toString();
    }
}
