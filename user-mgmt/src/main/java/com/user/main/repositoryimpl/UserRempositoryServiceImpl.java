package com.user.main.repositoryimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.main.entity.UserEntity;
import com.user.main.product.Dao.UserRepository;
import com.user.main.repository.UserRepositoryService;

@Service
public class UserRempositoryServiceImpl implements UserRepositoryService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUserDetails(UserEntity userEntity) {
		if (userEntity != null) {
			userRepository.save(userEntity);
		}
	}

	@Override
	public UserEntity findUserFirtNameAndPassword(String firtsName, String password) {
		UserEntity findByFirtNameAndPassword = null;
		if (firtsName != null && password != null) {
			findByFirtNameAndPassword = userRepository.findByFirtNameAndPassword(firtsName, password);
		}
		if (findByFirtNameAndPassword != null) {
			return findByFirtNameAndPassword;
		}
		return null;
	}

	@Override
	public UserEntity getUserByUserId(Integer id) {
		Optional<UserEntity> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

}
