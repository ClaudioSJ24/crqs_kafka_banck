package com.sanchez.bankingmysql.api.queries;

import com.sanchez.cqrs.core.querys.BaseQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FindAccountByHolderQuery extends BaseQuery {

    private String accountHolder;

    public FindAccountByHolderQuery(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accountHolder", accountHolder)
                .toString();
    }
}
