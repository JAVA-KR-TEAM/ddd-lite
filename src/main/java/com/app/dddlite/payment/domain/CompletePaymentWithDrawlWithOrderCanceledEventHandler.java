package com.app.dddlite.payment.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.dddlite.infra.sms.SmsClient;
import com.app.dddlite.infra.sms.SmsMessage;
import com.app.dddlite.order.domain.event.OrderCanceledEvent;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class CompletePaymentWithDrawlWithOrderCanceledEventHandler {
	private final SmsClient smsClient;

	@Async
	@EventListener
	public void handle(OrderCanceledEvent event) {
		// TODO Payment Withdrawl
		smsClient.send(new SmsMessage());
	}
}
