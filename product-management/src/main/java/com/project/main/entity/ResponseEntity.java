package com.project.main.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = 4272785706525575104L;

	private String message;
	private Object object;
	private Boolean value;

}
