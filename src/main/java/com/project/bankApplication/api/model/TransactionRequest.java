package com.project.bankApplication.api.model;


public interface TransactionRequest {
    String getMessageId();
    String getUserId();
    Transaction getTransaction();

    void setMessageId(String messageId);
    void setUserId(String userId);
    void setTransaction(Transaction transaction);
}