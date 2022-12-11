package com.project.main.service;

import com.project.main.dto.UserDTO;
import com.project.main.entity.ResponseEntity;

public interface UserService {

	ResponseEntity signUp(UserDTO userDTO);

}
