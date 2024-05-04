package com.project.bankApplication.service;

import com.project.bankApplication.api.model.*;
import com.project.bankApplication.api.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TransactionEventHandler {
    private final Map<String, Double> userBalances;
    private final Map<String, List<TransactionRequest>> userEvents;
    private final Map<String, Lock> userLocks;
    private final Error internalServerError;

    public TransactionEventHandler() {
        userBalances = new HashMap<>();
        userEvents = new HashMap<>();
        userLocks = new HashMap<>();
        internalServerError = new Error("Server encountered some error during the processing of the request", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private void addEvent(TransactionRequest event) {
        String userId = event.getUserId();
        List<TransactionRequest> userEvent = this.userEvents.getOrDefault(userId, new ArrayList<>());

        userEvent.add(event);
        this.userEvents.put(userId, userEvent);
    }

    public String processTransaction(WithdrawRequest withdrawRequest) {
        addEvent(withdrawRequest);

        String userId = withdrawRequest.getUserId();
        Double amount = withdrawRequest.getTransaction().getAmount(); //Can add support for multiple currencies.

        Lock userLock = userLocks.computeIfAbsent(userId, u -> new ReentrantLock());
        userLock.lock();
        try {
            ResponseCode responseCode = authorize(userId, amount);

            WithdrawResponse authorizationResponse = new WithdrawResponse(withdrawRequest.getMessageId(), withdrawRequest.getUserId(), withdrawRequest.getTransaction());
            authorizationResponse.getBalance().setAmount(userBalances.computeIfAbsent(userId, f -> 0.0));
            authorizationResponse.setResponseCode(responseCode);

            return authorizationResponse.toString();
        } catch (Exception exception) {
            return internalServerError.toString();
        } finally {
            userLock.unlock();
        }
    }

    public String processTransaction(DepositRequest depositRequest) {
        addEvent(depositRequest);

        String userId = depositRequest.getUserId();
        Double amount = depositRequest.getTransaction().getAmount(); //Can add support for multiple currencies.

        Lock userLock = userLocks.computeIfAbsent(userId, u -> new ReentrantLock());
        userLock.lock();
        try {
            load(userId, amount);

            DepositResponse loadResponse = new DepositResponse(depositRequest.getMessageId(), depositRequest.getUserId(), depositRequest.getTransaction());
            loadResponse.getBalance().setAmount(userBalances.get(userId));

            return loadResponse.toString();
        } catch (Exception exception) {
            return internalServerError.toString();
        } finally {
            userLock.unlock();
        }
    }

    private void load(String userId, double amount) {
        double balance = this.userBalances.getOrDefault(userId, 0.0);
        balance += amount;
        this.userBalances.put(userId, balance);
    }

    private ResponseCode authorize(String userId, double amount) {
        double balance = this.userBalances.getOrDefault(userId, 0.0);
        if (balance >= amount) {
            balance -= amount;
            this.userBalances.put(userId, balance);
        } else {
            return ResponseCode.DECLINED;
        }
        return ResponseCode.APPROVED;
    }
}
