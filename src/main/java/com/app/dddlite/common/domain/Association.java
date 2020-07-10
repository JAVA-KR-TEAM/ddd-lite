package com.app.dddlite.common.domain;

import lombok.Getter;

@Getter
public class Association<T> {
	private final Long id;

	public Association(Long id) {
		this.id = id;
	}
}
