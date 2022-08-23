package com.finite.service;

import com.finite.dto.CreateCustomerRequest;
import com.finite.dto.FraudCheckResponse;
import com.finite.model.Customer;
import com.finite.repo.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
		CustomerRepo customerRepo,
		RestTemplate restTemplate
) {

	public void createCustomer(CreateCustomerRequest createCustomerRequest) {
		Customer customer = Customer.builder()
				.firstName(createCustomerRequest.getFirstName())
				.lastName(createCustomerRequest.getLastName())
				.build();
		customerRepo.saveAndFlush(customer);

		fraudCheck(customer);
	}

	private void fraudCheck(Customer customer) {
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
				"http://localhost:8081/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class,
				customer.getId()
		);

		if (fraudCheckResponse == null || fraudCheckResponse.isFraud()) {
			throw new IllegalStateException("fraudulent customer " + customer);
		}
	}
}
