package com.user.main.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -915861238739046201L;

	private Integer userId;

	private String firtName;

	private String age;

	private String phoneNumber;

	private String city;

	private String email;

	private String password;

	private String isActive;

	private Long createdDate;

	private Long lastLoginDate;
}
