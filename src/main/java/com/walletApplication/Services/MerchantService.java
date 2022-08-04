package com.walletApplication.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletApplication.Dao.MerchantDao;
import com.walletApplication.Dto.MerchantCreationBody;
import com.walletApplication.Dto.MerchantUpdateEntity;
import com.walletApplication.Entities.MerchantEntity;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public List<MerchantEntity> getAllMerchant() {

		return merchantDao.findAll();
	}

	public MerchantEntity getById(String mobileNumber) {
		return merchantDao.findById(mobileNumber).get();

	}

	public MerchantEntity createMerchant(MerchantCreationBody merchant) {
		MerchantEntity newMerchant = new MerchantEntity();
		newMerchant.setDate(new Date());
		newMerchant.setMobileNumber(merchant.getMobileNumber());
		newMerchant.setPassword(merchant.getPassword());
		newMerchant.setName(merchant.getName());
		newMerchant.setAddress(merchant.getAddress());
		merchantDao.save(newMerchant);
		return newMerchant;
	}

	public MerchantEntity updateMerchant(String mobileNumber, MerchantUpdateEntity merchant) {
		MerchantEntity updateMerchant = merchantDao.getById(mobileNumber);
		updateMerchant.setName(merchant.getName());
		updateMerchant.setPassword(merchant.getPassword());
		updateMerchant.setAddress(merchant.getAddress());
		return merchantDao.save(updateMerchant);

	}

	public void deleteMerchant(String mobileNumber) {

		MerchantEntity entity = merchantDao.getById(mobileNumber);
		merchantDao.delete(entity);
	}

}
