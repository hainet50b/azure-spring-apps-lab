package com.programacho.logingester;

import java.time.LocalDateTime;

public record ExchangeLog(
        String type,
        String method,
        String endpoint,
        String status,
        String headers,
        String body,
        LocalDateTime timestamp
) {
}
