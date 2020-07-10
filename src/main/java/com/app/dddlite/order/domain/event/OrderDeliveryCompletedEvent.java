package com.app.dddlite.order.domain.event;

import com.app.dddlite.order.domain.Order;
import lombok.Getter;

@Getter
public class OrderDeliveryCompletedEvent {
	private final Order order;

	public OrderDeliveryCompletedEvent(Order order) {
		this.order = order;
	}

	public Long getOrderId() {
		return order.getId();
	}
}
