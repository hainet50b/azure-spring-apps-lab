package com.programacho.logingester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class LogIngesterListener {

    private final Logger log = LoggerFactory.getLogger(LogIngesterListener.class);

    private final ExchangeLogService service;

    public LogIngesterListener(ExchangeLogService service) {
        this.service = service;
    }

    @Bean
    public Consumer<Message<Map<String, Object>>> log() {
        return m -> {
            try {
                log.debug("メッセージを受信しました。データストアにメッセージを格納します。");

                Map<String, Object> payload = m.getPayload();

                service.log(new ExchangeLog(
                        (String) payload.get("type"),
                        (String) payload.get("method"),
                        (String) payload.get("endpoint"),
                        (String) payload.get("status"),
                        (String) payload.get("headers"),
                        (String) payload.get("body"),
                        LocalDateTime.now()
                ));
            } catch (RuntimeException e) {
                log.debug("データストアへのメッセージの格納に失敗しました。", e);
            }
        };
    }
}
