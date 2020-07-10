package com.app.dddlite.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dddlite.customer.domain.Customer;
import com.app.dddlite.order.command.OrderCommand;
import com.app.dddlite.order.domain.Order;
import com.app.dddlite.order.domain.OrderRepository;
import com.app.dddlite.order.domain.component.OrderMaker;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderMaker maker;

	public Order place(Customer customer, OrderCommand command) {
		Order order = maker.placeOrder(customer, command);
		return orderRepository.save(order);
	}

	public void pay(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.pay();
	}

	public void delivery(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.delivering();
		orderRepository.save(order);
	}

	public void complete(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.complete();
		orderRepository.save(order);
	}

	public void cancel(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.cancel();
		orderRepository.save(order);
	}
}
