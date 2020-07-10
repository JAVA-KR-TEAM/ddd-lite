package com.app.dddlite.common.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.app.dddlite.common.domain.Association;

@Converter(autoApply = true)
public class AssociationConverter implements AttributeConverter<Association<?>, Long> {
	@Override
	public Long convertToDatabaseColumn(Association<?> associationId) {
		return associationId.getId();
	}

	@Override
	public Association<?> convertToEntityAttribute(Long id) {
		return new Association<>(id);
	}
}
