package com.walletApplication.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletApplication.Dao.UserWalletDao;
import com.walletApplication.Dto.UserWalletCreationBody;
import com.walletApplication.Dto.UserWalletUpdateEntity;
import com.walletApplication.Entities.UserWalletEntity;
import com.walletApplication.Enum.UserStatus;

@Service
public class UserWalletService {
	@Autowired
	private UserWalletDao userWalletDao;

	public List<UserWalletEntity> getAllUserWallet() {

		return userWalletDao.findAll();
	}

	public UserWalletEntity getById(String userId) {
		return userWalletDao.findById(userId).get();

	}

	public UserWalletEntity createUserWallet(UserWalletCreationBody userWallet) {
		UserWalletEntity newUserWallet = new UserWalletEntity();
		newUserWallet.setUserId(userWallet.getUserId());
		newUserWallet.setAmount(userWallet.getAmount());
		newUserWallet.setStatus(UserStatus.ACTIVE.toString());
		userWalletDao.save(newUserWallet);
		return newUserWallet;
	}

	public UserWalletEntity updateUserWallet(String userId, UserWalletUpdateEntity userWallet) {
		UserWalletEntity wallet = userWalletDao.getById(userId);
		wallet.setAmount(userWallet.getAmount());
		wallet.setStatus(userWallet.getStatus());
		return userWalletDao.save(wallet);

	}

	public void deleteUserWallet(String userId) {

		UserWalletEntity entity = userWalletDao.getById(userId);
		userWalletDao.delete(entity);
	}

}
