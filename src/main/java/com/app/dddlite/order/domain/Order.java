package com.app.dddlite.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.app.dddlite.common.domain.Money;
import com.app.dddlite.order.domain.event.OrderCanceledEvent;
import com.app.dddlite.order.domain.event.OrderDeliveryCompletedEvent;
import com.app.dddlite.order.domain.event.OrderDeliveryStartedEvent;
import com.app.dddlite.payment.domain.PaymentInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AbstractAggregateRoot<Order> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Long id;
	@Embedded
	private Orderer orderer;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private OrderStatus status;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private List<OrderLine> orderLines = new ArrayList<>();
	@Embedded
	private DeliveryInfo deliveryInfo;
	@Column(name = "TOTAL_AMOUNT")
	private Money totalAmount;
	@CreatedDate
	@Column(name = "ORDERED_TIME")
	private LocalDateTime createAt;
	@LastModifiedDate
	@Column(name = "MODIFIED_TIME")
	private LocalDateTime modifiedAt;

	private Order(Orderer orderer, List<OrderLine> orderLines, DeliveryInfo deliveryInfo) {
		this.orderer = orderer;
		this.orderLines = orderLines;
		this.deliveryInfo = deliveryInfo;
		this.status = OrderStatus.PAYMENT_WAITING;
		this.totalAmount = calculateTotalAmount();
		this.createAt = LocalDateTime.now();
		this.modifiedAt = LocalDateTime.now();
	}

	private Money calculateTotalAmount() {
		return Money.sum(orderLines, OrderLine::getAmounts);
	}

	public static Order place(Orderer orderer, List<OrderLine> orderLines, DeliveryInfo deliveryInfo) {
		return new Order(orderer, orderLines, deliveryInfo);
	}

	public void pay() {
		isNotYetDelivered();
		this.status = OrderStatus.PREPARING;
	}

	public void delivering() {
		verifyShippingableStatus();
		this.status = OrderStatus.DELIVERING;
		registerEvent(new OrderDeliveryStartedEvent(this));
	}

	public void complete() {
		this.status = OrderStatus.DELIVERY_COMPLETE;
		registerEvent(new OrderDeliveryCompletedEvent(this));
	}

	public void cancel() {
		verifyNotYetDelivered();
		this.status = OrderStatus.CANCELED;
		registerEvent(new OrderCanceledEvent(this));
	}

	public void changeDeliveryInfo(DeliveryInfo newDeliveryInfo) {
		verifyNotYetDelivered();
		this.deliveryInfo = newDeliveryInfo;
	}

	public boolean isNotYetDelivered() {
		return status == OrderStatus.PAYMENT_WAITING || status == OrderStatus.PREPARING;
	}

	private void verifyShippingableStatus() {
		verifyNotYetDelivered();
		verifyNotCanceled();
	}

	private void verifyNotYetDelivered() {
		if(!isNotYetDelivered())
			throw new IllegalStateException("Already Delivered");
	}

	private void verifyNotCanceled() {
		if(status == OrderStatus.CANCELED)
			throw new IllegalStateException("Order Canceled");
	}
}
