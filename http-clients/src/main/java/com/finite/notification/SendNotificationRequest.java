package com.finite.notification;

import lombok.Builder;

@Builder
public record SendNotificationRequest(
		int toCustomerId,
		String message,
		String sender
) {
}
