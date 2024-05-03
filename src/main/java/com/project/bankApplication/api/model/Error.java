package com.project.bankApplication.api.model;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private String message;
    private Integer code;

    public Error() {
        message = "/";
        code = 404;
    }

    public Error(String message, Integer code) {
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
