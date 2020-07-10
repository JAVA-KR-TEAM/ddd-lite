package com.app.dddlite.common.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.app.dddlite.common.domain.Money;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
	@Override
	public Long convertToDatabaseColumn(Money money) {
		return money.getAmount().longValue();
	}

	@Override
	public Money convertToEntityAttribute(Long amount) {
		return Money.wons(amount);
	}
}
