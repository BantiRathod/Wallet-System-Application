package com.walletApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletApplication.Dao.MerchantWalletDao;
import com.walletApplication.Dto.MerchantWalletCreationBody;
import com.walletApplication.Dto.MerchantWalletUpdateEntity;
import com.walletApplication.Entities.MerchantWalletEntity;
import com.walletApplication.Enum.MerchantStatus;

@Service
public class MerchantWalletService {
	@Autowired
	private MerchantWalletDao merchantWalletDao;

	public List<MerchantWalletEntity> getAllMerchantWallet() {

		return merchantWalletDao.findAll();
	}

	public MerchantWalletEntity getById(String merchantId) {
		return merchantWalletDao.findById(merchantId).get();

	}

	public MerchantWalletEntity createMerchantWallet(MerchantWalletCreationBody merchantWallet) {
		MerchantWalletEntity newMerchantWallet = new MerchantWalletEntity();
		newMerchantWallet.setMerchantId(merchantWallet.getMerchantId());
		newMerchantWallet.setAmount(merchantWallet.getAmount());
		newMerchantWallet.setStatus(MerchantStatus.ACTIVE.toString());
		merchantWalletDao.save(newMerchantWallet);
		return newMerchantWallet;
	}

	public MerchantWalletEntity updateMerchantWallet(String merchantId, MerchantWalletUpdateEntity merchantWallet) {
		MerchantWalletEntity wallet = merchantWalletDao.getById(merchantId);
		wallet.setAmount(merchantWallet.getAmount());
		wallet.setStatus(merchantWallet.getStatus());
		return merchantWalletDao.save(wallet);

	}

	public void deleteMerchantWallet(String merchantId) {

		MerchantWalletEntity entity = merchantWalletDao.getById(merchantId);
		merchantWalletDao.delete(entity);
	}

}
