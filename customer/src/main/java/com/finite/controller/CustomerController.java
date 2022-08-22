package com.finite.controller;

import com.finite.dto.CreateCustomerRequest;
import com.finite.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
public record CustomerController(CustomerService customerService) {

	@PostMapping
	public void createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		log.info("create customer request: {}", createCustomerRequest);
		customerService.createCustomer(createCustomerRequest);
	}
}
