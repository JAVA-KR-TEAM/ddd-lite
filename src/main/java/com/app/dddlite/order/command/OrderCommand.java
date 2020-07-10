package com.app.dddlite.order.command;

import java.util.List;

import com.app.dddlite.order.domain.DeliveryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class OrderCommand {
	private DeliveryInfo deliveryInfo;
	private List<OrderLineCommand> orderLineCommands;
}
