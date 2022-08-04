package com.walletApplication.Dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walletApplication.Entities.TransactionEntity;

public interface TransactionDao extends JpaRepository<TransactionEntity, Integer> {

	TransactionEntity findById(int transactionId);
    @Query("Select t from TransactionEntity t where t.date between :startDate and :endDate ")
	List<TransactionEntity> findByDateBetweenAnd(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

}
