package com.finite.rabbitmq;

import com.finite.notification.SendNotificationRequest;
import com.finite.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

	private final NotificationService notificationService;

	@RabbitListener(queues = "${rabbitmq.queues.notification}")
	public void consume(SendNotificationRequest sendNotificationRequest) {
		log.info("Consumed {} from queue", sendNotificationRequest);
		notificationService.sendNotification(sendNotificationRequest);
	}
}
