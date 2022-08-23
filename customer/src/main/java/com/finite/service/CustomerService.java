package com.finite.service;

import com.finite.dto.CreateCustomerRequest;
import com.finite.fraud.FraudCheckResponse;
import com.finite.fraud.FraudClient;
import com.finite.model.Customer;
import com.finite.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
		CustomerRepo customerRepo,
		FraudClient fraudClient
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
		FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());

		if (fraudCheckResponse == null || fraudCheckResponse.isFraud()) {
			throw new IllegalStateException("fraudulent customer " + customer);
		}
	}
}
