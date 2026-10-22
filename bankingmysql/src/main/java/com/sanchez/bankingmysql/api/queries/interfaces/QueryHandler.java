package com.sanchez.bankingmysql.api.queries.interfaces;

import com.sanchez.bankingmysql.api.queries.FindAccountByHolderQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountByIdQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountWithBalanceQuery;
import com.sanchez.bankingmysql.api.queries.FindAllAccountsQuery;
import com.sanchez.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {

    List<BaseEntity> handler(FindAllAccountsQuery findAllAccountsQuery);
    List<BaseEntity> handler(FindAccountByIdQuery findAccountByIdQuery);
    List<BaseEntity> handler(FindAccountByHolderQuery findAccountByHolderQuery);
    List<BaseEntity> handler(FindAccountWithBalanceQuery findAccountWithBalanceQuery);
}
