package com.finite.controller;

import com.finite.dto.FraudCheckResponse;
import com.finite.service.FraudCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraud(@PathVariable int customerId) {
        boolean isFraud = fraudCheckService.isFraud(customerId);
        return new FraudCheckResponse(isFraud);
    }
}
