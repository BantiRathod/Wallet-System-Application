package com.walletApplication.Services;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walletApplication.Dao.UserDao;
import com.walletApplication.Dto.UserCreationBody;
import com.walletApplication.Dto.UserUpdateEntity;
import com.walletApplication.Entities.UserEntity;

@Transactional
@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	

	public List<UserEntity> getAllUser() {

		return userDao.findAll();
	}

	public UserEntity getById(String mobileNumber) {
		return userDao.findById(mobileNumber).get();

	}

	public UserEntity createUser(UserCreationBody user) {
		UserEntity newUser = new UserEntity();
		newUser.setDate(new Date());
		newUser.setMobileNumber(user.getMobileNumber());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setName(user.getName());
		userDao.save(newUser);
		
		return newUser;
	}

	public UserEntity updateUser(String mobileNumber, UserUpdateEntity user) {
		UserEntity users = userDao.getById(mobileNumber);
		users.setName(user.getName());
		users.setPassword(user.getPassword());
		return userDao.save(users);

	}

	public void deleteUser(String mobileNumber) {

		UserEntity entity = userDao.getById(mobileNumber);
		userDao.delete(entity);
	}

}
