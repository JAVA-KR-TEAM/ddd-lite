package com.app.dddlite.order.command;

import com.app.dddlite.common.domain.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderLineCommand {
	private Long productId;
	private int quantity;
	private Money price;

	@Builder
	public OrderLineCommand(Long productId, int quantity, Money price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
}
