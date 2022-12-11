package com.project.main.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceNotFountException extends RuntimeException {

	private static final long serialVersionUID = 7671435225293652223L;

	final String resourceName;
	final String filedName;
	final Long filedValue;

	public ResourceNotFountException(String resourceName, String filedName, long filedValue) {
		super(String.format("%s not found with %s : %s", resourceName, filedName, filedValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.filedValue = filedValue;
	}
}
