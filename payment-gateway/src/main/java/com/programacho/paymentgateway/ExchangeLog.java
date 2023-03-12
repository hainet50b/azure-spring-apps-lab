package com.programacho.paymentgateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExchangeLog(
        HttpMethod method,
        String endpoint,
        HttpStatus status,
        HttpHeaders headers,
        String body,
        LocalDateTime timestamp
) {

    public enum Type {

        REQUEST("REQUEST"),

        RESPONSE("RESPONSE");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
