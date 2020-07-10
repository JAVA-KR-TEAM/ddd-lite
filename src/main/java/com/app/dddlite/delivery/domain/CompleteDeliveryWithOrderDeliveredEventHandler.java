package com.app.dddlite.delivery.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.dddlite.infra.sms.SmsClient;
import com.app.dddlite.infra.sms.SmsMessage;
import com.app.dddlite.order.domain.event.OrderDeliveryCompletedEvent;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class CompleteDeliveryWithOrderDeliveredEventHandler {
	private final DeliveryRepository deliveryRepository;
	private final SmsClient smsClient;

	@Async
	@EventListener
	public void handle(OrderDeliveryCompletedEvent event) {
		Delivery delivery = deliveryRepository.findById(event.getOrderId()).orElseThrow(IllegalArgumentException::new);
		delivery.complete();
		smsClient.send(new SmsMessage());
	}
}
