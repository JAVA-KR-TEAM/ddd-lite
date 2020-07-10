package com.app.dddlite;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.dddlite.common.domain.Address;
import com.app.dddlite.common.domain.Money;
import com.app.dddlite.customer.domain.Customer;
import com.app.dddlite.order.command.OrderCommand;
import com.app.dddlite.order.command.OrderLineCommand;
import com.app.dddlite.order.domain.DeliveryInfo;
import com.app.dddlite.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
	private final OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer(1L, "Rebwon", Money.wons(1000000));
		OrderCommand command = OrderCommand.builder()
			.orderLineCommands(List.of(
				OrderLineCommand.builder()
					.productId(1L)
					.price(Money.wons(12500))
					.quantity(3)
					.build()
			))
			.deliveryInfo(
				new DeliveryInfo(
					new Address("123-41", "서울시 강남구 논현동", "12번길 34"),
					"현관문 앞에 배송 부탁드립니다."))
			.build();
		orderService.place(customer, command);

		orderService.pay(1L);

		orderService.delivery(1L);
	}

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(Application.class, args);
		log.info("APPLICATION FINISHED");
	}
}
