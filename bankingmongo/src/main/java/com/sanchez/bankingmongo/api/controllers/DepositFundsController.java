package com.sanchez.bankingmongo.api.controllers;


import com.sanchez.bankingmongo.api.command.DeposiFundsCommand;
import com.sanchez.bankingmongo.api.command.WithDrawFundsCommand;
import com.sanchez.common.dto.BaseResponse;
import com.sanchez.cqrs.core.exceptions.AggregateNotFoundException;
import com.sanchez.cqrs.core.infrastructure.interfaces.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/depositFounds")
public class DepositFundsController {

    private final Logger logger = Logger.getLogger(DepositFundsController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withDrawFunds(@PathVariable(value = "id") String id, @RequestBody DeposiFundsCommand deposiFundsCommand){

        try {

            deposiFundsCommand.setId(id);
            commandDispatcher.send(deposiFundsCommand);
            return  new ResponseEntity<>(new BaseResponse("Retiro exitoso"), HttpStatus.OK);

        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Request con errores - {0}", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error al procesar request {id}", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
