package com.finite.service;

import com.finite.model.FraudCheck;
import com.finite.repo.FraudCheckRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckRepo fraudCheckRepo) {

    public boolean isFraud(int customerId) {
        FraudCheck fraudCheck = FraudCheck.builder()
                .customerId(customerId)
                .isFraud(false)
                .createdAt(LocalDateTime.now())
                .build();
        return fraudCheckRepo.save(fraudCheck).isFraud();
    }
}
