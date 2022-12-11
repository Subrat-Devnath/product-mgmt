package com.user.main.repository;

import com.user.main.entity.UserEntity;

public interface UserRepositoryService {

	public UserEntity findUserFirtNameAndPassword(String firtsName, String password);

	public void saveUserDetails(UserEntity userEntity);

	public UserEntity getUserByUserId(Integer id);
}
