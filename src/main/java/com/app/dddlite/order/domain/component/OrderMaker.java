package com.app.dddlite.order.domain.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.dddlite.common.domain.Association;
import com.app.dddlite.customer.domain.Customer;
import com.app.dddlite.order.command.OrderCommand;
import com.app.dddlite.order.command.OrderLineCommand;
import com.app.dddlite.order.domain.Order;
import com.app.dddlite.order.domain.OrderLine;
import com.app.dddlite.order.domain.Orderer;

@Component
public class OrderMaker {

	public Order placeOrder(Customer customer, OrderCommand command) {
		List<OrderLine> orderLines = new ArrayList<>();
		for(OrderLineCommand each : command.getOrderLineCommands()) {
			orderLines.add(OrderLine.builder()
				.productId(new Association<>(each.getProductId()))
				.price(each.getPrice())
				.quantity(each.getQuantity())
				.build());
		}
		Orderer orderer = new Orderer(new Association<>(customer.getId()), customer.getName());
		return Order.place(orderer, orderLines, command.getDeliveryInfo());
	}
}
