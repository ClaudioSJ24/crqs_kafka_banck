package com.sanchez.bankingmysql.api.controllers;


import com.sanchez.bankingmysql.api.dto.AccountLookupResponse;
import com.sanchez.bankingmysql.api.dto.EqualityType;
import com.sanchez.bankingmysql.api.queries.FindAccountByHolderQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountByIdQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountWithBalanceQuery;
import com.sanchez.bankingmysql.api.queries.FindAllAccountsQuery;
import com.sanchez.bankingmysql.domain.BankAccount;
import com.sanchez.cqrs.core.infrastructure.interfaces.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/backAccountLookup")
public class AccountLookupController {

    private final Logger logger = Logger.getLogger(AccountLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){

        try {

            List<BankAccount> accountList = queryDispatcher.send(new FindAllAccountsQuery());
            if (accountList == null || accountList.size() == 0) {
                return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message(MessageFormat.format("Se realizo la consulta con exito", null))
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Errores al ejecutar la consulta";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id){

        try {

            List<BankAccount> accountList = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accountList == null || accountList.size() == 0) {
                return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message(MessageFormat.format("Se realizo la consulta con exito", null))
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Errores al ejecutar la consulta";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/byHolder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "accountHolder") String accountHolder){

        try {

            List<BankAccount> accountList = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
            if (accountList == null || accountList.size() == 0) {
                return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message(MessageFormat.format("Se realizo la consulta con exito", null))
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Errores al ejecutar la consulta";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/byWithBalance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountByWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType,
                                                                         @PathVariable(value = "balance") double balance)
    {

        try {

            List<BankAccount> accountList = queryDispatcher.send(new FindAccountWithBalanceQuery(balance, equalityType));
            if (accountList == null || accountList.size() == 0) {
                return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message(MessageFormat.format("Se realizo la consulta con exito", null))
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var safeErrorMessage = "Errores al ejecutar la consulta";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
