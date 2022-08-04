package com.walletApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walletApplication.Entities.MerchantWalletEntity;

public interface MerchantWalletDao extends JpaRepository<MerchantWalletEntity, String> {

}
