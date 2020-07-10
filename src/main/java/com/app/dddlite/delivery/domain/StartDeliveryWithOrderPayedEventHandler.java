package com.app.dddlite.delivery.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.dddlite.common.domain.Association;
import com.app.dddlite.infra.sms.SmsClient;
import com.app.dddlite.infra.sms.SmsMessage;
import com.app.dddlite.order.domain.event.OrderDeliveryStartedEvent;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class StartDeliveryWithOrderPayedEventHandler {
	private final DeliveryRepository deliveryRepository;
	private final SmsClient smsClient;

	@Async
	@EventListener
	public void handle(OrderDeliveryStartedEvent event) {
		Delivery delivery = Delivery.started(new Association<>(event.getOrderId()));
		deliveryRepository.save(delivery);
		smsClient.send(new SmsMessage());
	}
}
