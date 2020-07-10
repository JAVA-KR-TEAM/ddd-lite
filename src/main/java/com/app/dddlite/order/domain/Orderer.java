package com.app.dddlite.order.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.app.dddlite.common.domain.Association;
import com.app.dddlite.customer.domain.Customer;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode @Getter
public class Orderer {
	@Column(name = "ORDERER_ID")
	private Association<Customer> customerId;
	@Column(name = "ORDERER_NAME")
	private String name;

	public Orderer(Association<Customer> customerId, String name) {
		this.customerId = customerId;
		this.name = name;
	}
}
