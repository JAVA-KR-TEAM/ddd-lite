package com.app.dddlite.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.dddlite.common.domain.Association;
import com.app.dddlite.common.domain.Money;
import com.app.dddlite.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "ORDER_LINES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "PRODUCT_ID")
	private Association<Product> productId;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "AMOUNTS")
	private Money amounts;
	@Column(name = "PRICE")
	private Money price;

	@Builder
	public OrderLine(Association<Product> productId, Money price, int quantity) {
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.amounts = calculateAmounts();
	}

	private Money calculateAmounts() {
		return price.times(quantity);
	}
}
