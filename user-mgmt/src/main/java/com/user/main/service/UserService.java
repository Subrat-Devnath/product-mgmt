package com.user.main.service;

import com.user.main.dto.UserDTO;
import com.user.main.entity.ResponseEntity;

public interface UserService {

	public ResponseEntity signUp(UserDTO userDTO);

	public ResponseEntity login(String userName, String password);

	public UserDTO getUser(Integer id);
}
