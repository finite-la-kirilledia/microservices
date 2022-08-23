package com.finite.service;

import com.finite.dto.CreateCustomerRequest;
import com.finite.fraud.FraudCheckResponse;
import com.finite.fraud.FraudClient;
import com.finite.model.Customer;
import com.finite.notification.NotificationClient;
import com.finite.notification.SendNotificationRequest;
import com.finite.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
		CustomerRepo customerRepo,
		FraudClient fraudClient,
		NotificationClient notificationClient
) {

	public void createCustomer(CreateCustomerRequest createCustomerRequest) {
		Customer customer = Customer.builder()
				.firstName(createCustomerRequest.getFirstName())
				.lastName(createCustomerRequest.getLastName())
				.build();
		customerRepo.saveAndFlush(customer);

		fraudCheck(customer);
		sendNotification(customer); // todo: make it async
	}

	private void fraudCheck(Customer customer) {
		FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());

		if (fraudCheckResponse == null || fraudCheckResponse.isFraud()) {
			throw new IllegalStateException("fraudulent customer " + customer);
		}
	}

	private void sendNotification(Customer customer) {
		SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
				.toCustomerId(customer.getId())
				.message("Hi")
				.sender("finite la kirilledia")
				.build();
		notificationClient.sendNotification(sendNotificationRequest);
	}
}
