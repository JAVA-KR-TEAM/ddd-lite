package com.app.dddlite.order.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.app.dddlite.common.domain.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Getter
public class DeliveryInfo {
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "zipCode", column = @Column(name = "DELIVERING_ZIP_CODE")),
		@AttributeOverride(name = "address1", column = @Column(name = "DELIVERING_ADDR1")),
		@AttributeOverride(name = "address2", column = @Column(name = "DELIVERING_ADDR2"))
	})
	private Address address;
	@Column(name = "DELIVERING_MESSAGE")
	private String message;
}
