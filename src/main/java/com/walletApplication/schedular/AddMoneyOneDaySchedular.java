package com.walletApplication.schedular;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.walletApplication.Dao.MerchantWalletDao;
import com.walletApplication.Dao.TransactionDao;
import com.walletApplication.Entities.MerchantEntity;
import com.walletApplication.Entities.MerchantWalletEntity;
import com.walletApplication.Entities.Settelment;
import com.walletApplication.Entities.TransactionEntity;
import com.walletApplication.Dao.SettelmentRepository;

@Service
public class AddMoneyOneDaySchedular {

	@Autowired
	SettelmentRepository SettelmentDao;

	@Autowired
	TransactionDao transactionDao;

	private static final Logger logger = LoggerFactory.getLogger(AddMoneyOneDaySchedular.class);

	@Scheduled(cron = "0 */2 * * * *")
	public void payAfterOneDay() {

		Date startDate;
		Date endDate;

		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY, 00);
		cal1.set(Calendar.MINUTE, 00);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 00);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.HOUR_OF_DAY, 23);
		cal2.set(Calendar.MINUTE, 59);
		cal2.set(Calendar.SECOND, 59);
		cal2.set(Calendar.MILLISECOND, 999);

		startDate = cal1.getTime();
		endDate = cal2.getTime();
		int count = 0;
		long amount = 0;

		List<TransactionEntity> allTransactions = transactionDao.findByDateBetweenAnd(startDate, endDate);
		List<TransactionEntity> merchantTransactions = new ArrayList<>();

		// filtering all merchant records
		for (TransactionEntity t : allTransactions) {
			if (t.getTransactionType().equalsIgnoreCase("MERCHANT")) {
				merchantTransactions.add(t);
			}
		}

		for (int i = 0; i < merchantTransactions.size(); i++) {
			for (int j = 0; j < merchantTransactions.size(); j++) {
				if (merchantTransactions.get(i).getReceiverUserId()
						.equalsIgnoreCase(merchantTransactions.get(i).getReceiverUserId())) {
					count++;
					amount += merchantTransactions.get(j).getAmount();
				}
			}

			SettelmentDao
					.save(new Settelment(amount, count, merchantTransactions.get(i).getReceiverUserId(), "SETTELED"));
			count = 0;
			amount = 0;

			logger.info("schedualed ");
		}
	}
}
