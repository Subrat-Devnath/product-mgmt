package com.project.main.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Charges implements Serializable{
	
	private static final long serialVersionUID = 2655810320486655234L;
	
	private Double gst;

	private Integer deliveryCharge;	

}
