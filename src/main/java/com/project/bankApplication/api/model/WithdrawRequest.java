package com.project.bankApplication.api.model;

import java.util.HashMap;
import java.util.Map;

public class WithdrawRequest implements TransactionRequest {
    private String messageId;
    private String userId;
    private Transaction transaction;

    public WithdrawRequest() {
        messageId = "/";
        userId = "/";
    }

    public WithdrawRequest(String messageId, String userId, Transaction transaction) {
        this.messageId = messageId;
        this.userId = userId;
        this.transaction = new Transaction(transaction);
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setTransaction(Transaction transaction) {
        this.transaction = new Transaction(transaction);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String toString() {
        Map<String, String> response = new HashMap<>();

        response.put("messageId", messageId);
        response.put("userId", userId);
        response.put("transaction", transaction.toString());

        return response.toString();
    }

}
