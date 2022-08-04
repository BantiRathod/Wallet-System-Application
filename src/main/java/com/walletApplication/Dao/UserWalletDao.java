package com.walletApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletApplication.Entities.UserWalletEntity;


public interface UserWalletDao extends JpaRepository<UserWalletEntity, String> {

}
