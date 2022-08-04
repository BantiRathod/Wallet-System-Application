package com.walletApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletApplication.Entities.MerchantEntity;

public interface MerchantDao extends JpaRepository<MerchantEntity, String> {

}
