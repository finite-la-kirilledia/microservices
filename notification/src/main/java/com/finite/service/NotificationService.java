package com.finite.service;

import com.finite.model.Notification;
import com.finite.notification.SendNotificationRequest;
import com.finite.repo.NotificationRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepo notificationRepo) {

	public void sendNotification(SendNotificationRequest sendNotificationRequest) {
		Notification notification = Notification.builder()
				.message(sendNotificationRequest.message())
				.sender(sendNotificationRequest.sender())
				.toCustomerId(sendNotificationRequest.toCustomerId())
				.sentAt(LocalDateTime.now())
				.build();
		notificationRepo.save(notification);
	}
}
