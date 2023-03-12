package com.programacho.paymentgateway;

import com.programacho.paymentgateway.credit.CreditAuthorizeRequest;
import com.programacho.paymentgateway.credit.CreditAuthorizeResponse;
import com.programacho.paymentgateway.credit.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final Logger log = LoggerFactory.getLogger(CreditController.class);

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/authorize")
    public PaymentGatewayCreditAuthorizeResponse authorize(@RequestBody PaymentGatewayCreditAuthorizeRequest request) {
        log.info("クレジットシステムに与信の取得を依頼します。");

        final CreditAuthorizeResponse response = creditService.authorize(new CreditAuthorizeRequest(
                request.user(),
                request.token(),
                request.amount()
        ));

        log.info("クレジットシステムから与信の結果を受領しました。");

        return new PaymentGatewayCreditAuthorizeResponse(
                response.result(),
                response.id(),
                response.errorCode()
        );
    }
}
