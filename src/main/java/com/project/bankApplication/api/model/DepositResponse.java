package com.project.bankApplication.api.model;

import java.util.HashMap;
import java.util.Map;

public class DepositResponse {
    private String messageId;
    private String userId;
    private Transaction balance;

    public DepositResponse() {
        messageId = "/";
        userId = "/";
    }

    public DepositResponse(String messageId, String userId, Transaction balance) {
        this.messageId = messageId;
        this.userId = userId;
        this.balance = new Transaction(balance);
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

    public Transaction getBalance() {
        return balance;
    }

    public void setBalance(Transaction balance) {
        this.balance = new Transaction(balance);
    }

    public String toString() {
        Map<String, String> response = new HashMap<>();

        response.put("messageId", messageId);
        response.put("userId", userId);
        response.put("balance", balance.toString());

        return response.toString();
    }

}
