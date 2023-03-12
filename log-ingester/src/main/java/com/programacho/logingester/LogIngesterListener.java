package com.programacho.logingester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class LogIngesterListener {

    private final Logger log = LoggerFactory.getLogger(LogIngesterListener.class);

    @Bean
    public Consumer<String> log() {
        return log::info;
    }
}