package com.walletApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletApplication.Entities.UserEntity;



public interface UserDao extends JpaRepository<UserEntity, String> {

}
