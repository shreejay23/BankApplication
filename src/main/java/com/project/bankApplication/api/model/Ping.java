package com.project.bankApplication.api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Ping {
    private static String serverTime;
    public Ping() {
        serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void updateServerTime() {
        serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String toString() {
        Map<String, String> response = new HashMap<>();
        response.put("serverTime", serverTime);
        return response.toString();
    }
}
