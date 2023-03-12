package com.programacho.paymentgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final Logger log = LoggerFactory.getLogger(CreditController.class);

    private final StreamBridge streamBridge;

    public CreditController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping("/authorize")
    public String authorize() {
        // TODO: 対向決済機関への通信をメッセージブローカー経由で記録する機能を実装する。
        streamBridge.send("log-out-0", "クレジットシステムに与信の取得を依頼します。");

        log.info("クレジットシステムに与信の取得を依頼します。");

        return "ok";
    }
}
