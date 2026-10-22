package com.sanchez.bankingmysql.api.queries.implement;

import com.sanchez.bankingmysql.api.dto.EqualityType;
import com.sanchez.bankingmysql.api.queries.FindAccountByHolderQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountByIdQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountWithBalanceQuery;
import com.sanchez.bankingmysql.api.queries.FindAllAccountsQuery;
import com.sanchez.bankingmysql.api.queries.interfaces.QueryHandler;
import com.sanchez.bankingmysql.domain.BankAccount;
import com.sanchez.bankingmysql.domain.interfaces.AccountRepository;
import com.sanchez.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<BaseEntity> handler(FindAllAccountsQuery findAllAccountsQuery) {

        Iterable<BankAccount> bankAccounts = accountRepository.findAll();
        List<BaseEntity> baseEntityList = new ArrayList<>();
        bankAccounts.forEach(baseEntityList::add);
        return baseEntityList;
    }

    @Override
    public List<BaseEntity> handler(FindAccountByIdQuery findAccountByIdQuery) {

        var bankAccount = accountRepository.findById(findAccountByIdQuery.getId());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> baseEntityList = new ArrayList<>();
        baseEntityList.add(bankAccount.get());
        return baseEntityList;
    }

    @Override
    public List<BaseEntity> handler(FindAccountByHolderQuery findAccountByHolderQuery) {
        var bankAccount = accountRepository.findById(findAccountByHolderQuery.getAccountHolder());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> baseEntityList = new ArrayList<>();
        baseEntityList.add(bankAccount.get());
        return baseEntityList;
    }

    @Override
    public List<BaseEntity> handler(FindAccountWithBalanceQuery findAccountWithBalanceQuery) {
        return findAccountWithBalanceQuery.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(findAccountWithBalanceQuery.getBalance())
                : accountRepository.findByBalanceLessThan(findAccountWithBalanceQuery.getBalance());
    }
}

