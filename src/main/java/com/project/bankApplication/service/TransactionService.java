package com.project.bankApplication.service;

import com.project.bankApplication.api.model.WithdrawRequest;
import com.project.bankApplication.api.model.DepositRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionEventHandler eventHandler;

    public String processTransaction(WithdrawRequest withdrawRequest) {
        return eventHandler.processTransaction(withdrawRequest);
    }

    public String processTransaction(DepositRequest depositRequest) {
        return eventHandler.processTransaction(depositRequest);
    }
}
