package com.project.bankApplication.api.model;

import java.util.HashMap;
import java.util.Map;

public class WithdrawResponse {
    private String messageId;
    private String userId;
    private ResponseCode responseCode;
    private Transaction balance;

    public WithdrawResponse() {
        messageId = "/";
        userId = "/";
    }

    public WithdrawResponse(String messageId, String userId, Transaction transactionTransaction) {
        this.messageId = messageId;
        this.userId = userId;
        this.balance = new Transaction(transactionTransaction);
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
        response.put("responseCode", String.valueOf(responseCode));

        return response.toString();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public void setBalanceAmount(Double balance) {
        this.balance.setAmount(balance);
    }

}
