package com.project.bankApplication.api.controller;

import com.project.bankApplication.api.model.*;
import com.project.bankApplication.api.model.Error;
import com.project.bankApplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    private final Error serviceUnAvailable;
    private final Error internalServerError;
    private final Ping ping;

    public TransactionController() {
        internalServerError = new Error("Server Error, Could not process the request", HttpStatus.INTERNAL_SERVER_ERROR);
        serviceUnAvailable = new Error("Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        ping = new Ping();
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testServiceAvailability() {
        try {
            if(transactionService == null) {
                return new ResponseEntity<>(serviceUnAvailable.toString(), serviceUnAvailable.getCode());
            }
            ping.updateServerTime();
        } catch (Exception exception) {
            return new ResponseEntity<>(internalServerError.toString(), internalServerError.getCode());
        }
        return new ResponseEntity<>(ping.toString(), HttpStatus.OK);
    }

    @PutMapping(value = "/load/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loadMoney(@PathVariable String messageId, @RequestBody DepositRequest depositRequest) {
        try {
            if(transactionService != null) {
//                System.out.println(depositRequest);
                String responseMessage = transactionService.processTransaction(depositRequest);
//                System.out.println(responseMessage);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(serviceUnAvailable.toString(), serviceUnAvailable.getCode());
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(internalServerError.toString(), internalServerError.getCode());
        }
    }

    @PutMapping(value = "/authorization/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authorizeMoney(@PathVariable String messageId, @RequestBody WithdrawRequest withdrawRequest) {
        try {
            if(transactionService != null) {
                String responseMessage = transactionService.processTransaction(withdrawRequest);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(serviceUnAvailable.toString(), serviceUnAvailable.getCode());
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(internalServerError.toString(), internalServerError.getCode());
        }
    }
}
