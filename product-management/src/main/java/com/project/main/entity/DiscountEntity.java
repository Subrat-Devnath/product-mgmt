package com.project.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "Discount")
@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DiscountEntity implements Serializable {

	private static final long serialVersionUID = 6076829919322099087L;

	@Id
	@Column(name = "PRODUCT_CATEGORY")
	private String productCategory;

	@Column(name = "DISCOUNT")
	private Double discount;

	@Column(name = "GST")
	private Double gst;

	@Column(name = "DELIVERY_CHARGE")
	private Integer deliveryCharge;

	
}
