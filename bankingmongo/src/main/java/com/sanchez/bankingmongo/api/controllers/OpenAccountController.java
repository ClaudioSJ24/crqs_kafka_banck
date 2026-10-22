package com.sanchez.bankingmongo.api.controllers;

import com.sanchez.bankingmongo.api.command.OpenAccountCommand;
import com.sanchez.bankingmongo.api.dto.OpenAccountResponse;
import com.sanchez.common.dto.BaseResponse;
import com.sanchez.cqrs.core.infrastructure.interfaces.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/openBankAccount")
public class OpenAccountController {

    private Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand openAccountCommand){
        var id = UUID.randomUUID().toString();

        try {
            commandDispatcher.send(openAccountCommand);
            return new ResponseEntity<>(new OpenAccountResponse("La cuenta se creo exitosamente ",id), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Error al generar la cuenta - {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            var safeErrorMessage = MessageFormat.format("Error al procesar request - {0} ", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
