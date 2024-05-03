package com.project.bankApplication.api.model;

import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private Double amount;
    private String currency;
    private DebitCredit debitOrCredit;

    public Transaction() {
        amount = 0.0;
        currency = "-";
    }

    public Transaction(Transaction object) {
        this(object.getAmount(), object.getCurrency(), object.debitOrCredit);
    }

    public Transaction(Double amount, String currency, DebitCredit debitOrCredit) {
        this.amount = amount;
        this.currency = currency;
        this.debitOrCredit = debitOrCredit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public DebitCredit getDebitOrCredit() {
        return debitOrCredit;
    }

    public void setDebitOrCredit(DebitCredit debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    public String toString() {
        Map<String, String> response = new HashMap<>();
        response.put("amount", String.valueOf(amount));
        response.put("currency", currency);
        response.put("debitOrCredit", String.valueOf(debitOrCredit));
        return response.toString();
    }
}
