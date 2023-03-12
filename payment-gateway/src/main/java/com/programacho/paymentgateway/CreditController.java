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
        // TODO: リクエストボディとレスポンスボディをメッセージブローカー経由で記録する機能を実装する。
        streamBridge.send("log-out-0", "クレジット与信を取得します。");

        log.info("クレジット与信を取得します。");

        return "ok";
    }
}
