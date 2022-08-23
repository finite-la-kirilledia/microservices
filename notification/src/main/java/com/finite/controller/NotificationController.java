package com.finite.controller;

import com.finite.notification.SendNotificationRequest;
import com.finite.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@Slf4j
public record NotificationController(NotificationService notificationService) {

	@PostMapping
	public void sendNotification(@RequestBody SendNotificationRequest sendNotificationRequest) {
		log.info("send notification request {}", sendNotificationRequest);
		notificationService.sendNotification(sendNotificationRequest);
	}
}
