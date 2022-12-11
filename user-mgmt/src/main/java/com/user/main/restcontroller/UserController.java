package com.user.main.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.main.dto.UserDTO;
import com.user.main.entity.ResponseEntity;
import com.user.main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/sign/up/user")
	ResponseEntity signUp(@RequestBody UserDTO userDTO) {
		return userService.signUp(userDTO);
	}

	@PostMapping("/login/user")
	ResponseEntity login(@RequestBody UserDTO userDTO) {
		return userService.login(userDTO.getFirtName(), userDTO.getPassword());
	}

	@GetMapping("/get/user/{id}")
	UserDTO getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

}
