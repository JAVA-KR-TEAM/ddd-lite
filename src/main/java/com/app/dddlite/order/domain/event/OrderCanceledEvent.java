package com.app.dddlite.order.domain.event;

import com.app.dddlite.order.domain.Order;
import lombok.Getter;

@Getter
public class OrderCanceledEvent {
	private final Order order;

	public OrderCanceledEvent(Order order) {
		this.order = order;
	}

	public Long getOrderId() {
		return order.getId();
	}
}
