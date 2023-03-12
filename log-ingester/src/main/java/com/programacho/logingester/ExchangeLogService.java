package com.programacho.logingester;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExchangeLogService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ExchangeLogService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void log(ExchangeLog log) {
        jdbcTemplate.update("INSERT INTO exchange_log (type, method, endpoint, status, header, body, timestamp) VALUES (:type, :method, :endpoint, :status, :header, :body, :timestamp)", Map.of(
                "type", log.type(),
                "method", log.method(),
                "endpoint", log.endpoint(),
                "status", log.status() != null ? log.status() : "",
                "header", log.headers(),
                "body", log.body() != null ? log.body() : "",
                "timestamp", log.timestamp()
        ));
    }
}
