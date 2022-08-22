package com.finite.service;

import com.finite.dto.CreateCustomerRequest;
import com.finite.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	public void createCustomer(CreateCustomerRequest createCustomerRequest) {
		Customer customer = Customer.builder()
				.firstName(createCustomerRequest.getFirstName())
				.lastName(createCustomerRequest.getLastName())
				.build();

		//todo save to db
	}
}
