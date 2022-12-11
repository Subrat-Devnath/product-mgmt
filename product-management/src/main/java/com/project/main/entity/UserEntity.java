package com.project.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "user")
@Data
@ToString
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -323192376060704598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer userId;

	@Column(name = "NAME")
	private String firtsName;

	@Column(name = "AGE")
	private String age;

	@Column(name = "PHONE_NO")
	private String phoneNumber;

	@Column(name = "CITY")
	private String city;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "IS_ACTIVE")
	private String isActive;

	@Column(name = "CREATE_DATE")
	private Long createdDate;

	@Column(name = "Last_Login_Date")
	private Long lastLoginDate;

}
