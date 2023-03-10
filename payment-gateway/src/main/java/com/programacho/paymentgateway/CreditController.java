package com.programacho.paymentgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final Logger log = LoggerFactory.getLogger(CreditController.class);

    @PostMapping("/authorize")
    public String authorize() {
        log.info("クレジット与信を取得します。");

        return "ok";
    }
}
