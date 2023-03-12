package com.programacho.paymentgateway.credit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programacho.paymentgateway.ExchangeLog;
import com.programacho.paymentgateway.ExchangeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CreditService {

    private final Logger log = LoggerFactory.getLogger(CreditService.class);

    private final RestTemplate restTemplate;

    private final ExchangeLogService exchangeLogService;

    private final ObjectMapper mapper;

    public CreditService(
            RestTemplate restTemplate,
            ExchangeLogService exchangeLogService,
            ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.exchangeLogService = exchangeLogService;
        this.mapper = mapper;
    }

    public CreditAuthorizeResponse authorize(CreditAuthorizeRequest request) {
        try {
            log.info("クレジットカードシステムに通信を開始します。");

            RequestEntity<CreditAuthorizeRequest> requestEntity = RequestEntity
                    .post("/authorize")
                    .body(request);

            String endpoint = restTemplate.getUriTemplateHandler().expand("/authorize").toString();

            exchangeLogService.preExchange(new ExchangeLog(
                    requestEntity.getMethod(),
                    endpoint,
                    null,
                    HttpHeaders.EMPTY,
                    mapper.writeValueAsString(request),
                    LocalDateTime.now()
            ));

            final ResponseEntity<CreditAuthorizeResponse> responseEntity = restTemplate.exchange(
                    requestEntity,
                    CreditAuthorizeResponse.class
            );

            exchangeLogService.postExchange(new ExchangeLog(
                    requestEntity.getMethod(),
                    endpoint,
                    responseEntity.getStatusCode(),
                    responseEntity.getHeaders(),
                    mapper.writeValueAsString(responseEntity.getBody()),
                    LocalDateTime.now()
            ));

            log.info("クレジットカードシステムへの通信が完了しました。");

            return responseEntity.getBody();
        } catch (JsonProcessingException e) {
            log.warn("オブジェクトのシリアライズまたはデシリアライズに失敗しました。");

            throw new RuntimeException(e);
        }
    }
}
