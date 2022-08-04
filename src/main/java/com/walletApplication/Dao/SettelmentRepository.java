package com.walletApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletApplication.Entities.MerchantEntity;
import com.walletApplication.Entities.Settelment;

public interface SettelmentRepository extends JpaRepository<Settelment, String> {

}
