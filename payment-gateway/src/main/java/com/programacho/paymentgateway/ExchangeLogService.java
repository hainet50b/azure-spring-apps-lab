package com.programacho.paymentgateway;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExchangeLogService {

    private final StreamBridge streamBridge;

    public ExchangeLogService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void preExchange(ExchangeLog exchangeLog) {
        log(
                ExchangeLog.Type.REQUEST,
                exchangeLog
        );
    }

    public void postExchange(ExchangeLog exchangeLog) {
        log(
                ExchangeLog.Type.RESPONSE,
                exchangeLog
        );
    }

    private void log(
            ExchangeLog.Type type,
            ExchangeLog exchangeLog) {
        streamBridge.send("log-out-0", Map.of(
                "type", type.getValue(),
                "method", exchangeLog.method(),
                "endpoint", exchangeLog.endpoint(),
                "status", exchangeLog.status() != null ? exchangeLog.status().value() : "",
                "headers", exchangeLog.headers(),
                "body", exchangeLog.body() != null ? exchangeLog.body() : "",
                "timestamp", exchangeLog.timestamp()
        ));
    }
}
