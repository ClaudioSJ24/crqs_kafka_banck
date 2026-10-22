package com.sanchez.bankingmongo;

import com.sanchez.bankingmongo.api.command.CloseAccountCommand;
import com.sanchez.bankingmongo.api.command.DeposiFundsCommand;
import com.sanchez.bankingmongo.api.command.OpenAccountCommand;
import com.sanchez.bankingmongo.api.command.WithDrawFundsCommand;
import com.sanchez.bankingmongo.api.interfaces.CommandHandler;
import com.sanchez.cqrs.core.infrastructure.interfaces.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

@PostConstruct
public void registerHandlers(){
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeposiFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithDrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
}

}
