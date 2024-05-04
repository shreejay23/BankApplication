package com.project.bankApplication.api.model;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private String message;
    private HttpStatus code;

    public Error() {
        message = "/";
        code = HttpStatus.NOT_FOUND;
    }

    public Error(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }

    public String toString() {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("code", String.valueOf(code));
        return response.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
