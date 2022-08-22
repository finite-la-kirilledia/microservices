package com.finite.service;

import com.finite.dto.CreateCustomerRequest;
import com.finite.model.Customer;
import com.finite.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepo customerRepo) {

	public void createCustomer(CreateCustomerRequest createCustomerRequest) {
		Customer customer = Customer.builder()
				.firstName(createCustomerRequest.getFirstName())
				.lastName(createCustomerRequest.getLastName())
				.build();

		customerRepo.save(customer);
	}
}
