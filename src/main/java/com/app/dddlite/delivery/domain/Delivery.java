package com.app.dddlite.delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.dddlite.common.domain.Association;
import com.app.dddlite.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DELIVERIES")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DELIVERY_ID")
	private Long id;
	@Column(name = "ORDER_ID")
	private Association<Order> orderId;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private DeliveryStatus status;

	private Delivery(Association<Order> orderId, DeliveryStatus status) {
		this.orderId = orderId;
		this.status = status;
	}

	public static Delivery started(Association<Order> orderId) {
		return new Delivery(orderId, DeliveryStatus.DELIVERING);
	}

	public void complete() {
		this.status = DeliveryStatus.DELIVERING_COMPLETE;
	}
}
