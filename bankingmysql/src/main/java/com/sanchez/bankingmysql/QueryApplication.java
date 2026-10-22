package com.sanchez.bankingmysql;

import com.sanchez.bankingmysql.api.queries.FindAccountByHolderQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountByIdQuery;
import com.sanchez.bankingmysql.api.queries.FindAccountWithBalanceQuery;
import com.sanchez.bankingmysql.api.queries.FindAllAccountsQuery;
import com.sanchez.bankingmysql.api.queries.interfaces.QueryHandler;
import com.sanchez.cqrs.core.infrastructure.interfaces.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){

		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handler);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handler);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handler);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handler);
	}

}
