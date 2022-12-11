package com.user.main.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.main.dto.UserDTO;
import com.user.main.entity.ResponseEntity;
import com.user.main.entity.UserEntity;
import com.user.main.repository.UserRepositoryService;
import com.user.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepositoryService userRepositoryService;

	@Override
	public ResponseEntity signUp(UserDTO userDTO) {
		logger.info("User sign up and userName : {}", userDTO.getFirtName());

		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

		if (userEntity != null) {
			saveUserDetails(userEntity);
		}

		logger.info("User sign up sucessfully : {}");
		return new ResponseEntity("sign up properly", null, true);
	}

	@Override
	public ResponseEntity login(String userName, String password) {
		UserDTO findUserByUserNameAndPassword = findUserByUserNameAndPassword(userName, password);
		if (findUserByUserNameAndPassword != null) {
			return new ResponseEntity("User login Successfully", null, true);
		}
		return new ResponseEntity("Somthing went wrong", null, false);
	}

	void saveUserDetails(UserEntity userEntity) {
		if (userEntity != null) {
			userRepositoryService.saveUserDetails(userEntity);
		}
	}

	UserDTO findUserByUserNameAndPassword(String userName, String password) {
		UserEntity userEntity = userRepositoryService.findUserFirtNameAndPassword(userName, password);
		if (userEntity != null) {
			return modelMapper.map(userEntity, UserDTO.class);
		}
		return null;
	}

	@Override
	public UserDTO getUser(Integer id) {
		UserEntity userEntity = getUserByUserId(id);
		if (userEntity != null) {
			return modelMapper.map(userEntity, UserDTO.class);
		}
		return null;
	}

	private UserEntity getUserByUserId(Integer id) {
		return userRepositoryService.getUserByUserId(id);
	}
}
