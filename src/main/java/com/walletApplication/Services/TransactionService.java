package com.walletApplication.Services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletApplication.Dao.MerchantWalletDao;
import com.walletApplication.Dao.TransactionDao;
import com.walletApplication.Dao.UserWalletDao;
import com.walletApplication.Dto.TransactionCreationBody;
import com.walletApplication.Dto.TransactionUpdateEntity;
import com.walletApplication.Dto.ZomatoTransactionsRequestBody;
import com.walletApplication.Entities.MerchantWalletEntity;
import com.walletApplication.Entities.TransactionEntity;
import com.walletApplication.Entities.UserWalletEntity;
import com.walletApplication.Entities.Wallet;
import com.walletApplication.Enum.MerchantStatus;
import com.walletApplication.Enum.TransactionStatus;
import com.walletApplication.Enum.TransactionType;

@Service
public class TransactionService {
	
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private UserWalletDao userWalletDao;

	@Autowired
	private MerchantWalletDao merchantWalletDao;

	@Autowired
	private UserWalletService userWalletService;

	@Autowired
	private MerchantWalletService merchantWalletService;

	public List<TransactionEntity> getAllTransaction() {

		return transactionDao.findAll();
	}

	public TransactionEntity getById(int transactionId) {
		return transactionDao.findById(transactionId);

	}


	public TransactionEntity sendMoneyToUser(TransactionCreationBody transaction) throws Exception {
		UserWalletEntity senderWallet ;
		UserWalletEntity receiverWallet;
		senderWallet = userWalletService.getById(transaction.getSenderUserId());
		receiverWallet = userWalletService.getById(transaction.getReceiverUserId());
		
		logger.info("sender wallet " + senderWallet.toString());
		logger.info("receiver wallet " + receiverWallet.toString());
		
		TransactionEntity newTransaction = createTransaction(transaction);
		newTransaction.setTransactionType(TransactionType.USER.toString());
		newTransaction = updateWallet(senderWallet, receiverWallet, newTransaction, transaction);
		userWalletDao.save(senderWallet);
		userWalletDao.save(receiverWallet);
		transactionDao.save(newTransaction);
		return newTransaction;
	}

	public void saveChanges(UserWalletEntity tempSenderWallet, MerchantWalletEntity tempReceiverWallet,TransactionEntity newTransaction) throws Exception
	{
			
	logger.info("updated user wallet : {}", userWalletDao.save(tempSenderWallet));
	logger.info("before throw error");
	boolean n=true;
	if(n)
	    throw new Exception("before saving updated merchant wallet exception OCCURED");
	
	merchantWalletDao.save(tempReceiverWallet);
		
	}

	@Transactional
	public TransactionEntity sendMoneyToMerchant(TransactionCreationBody transaction) throws Exception {

		//TO TAKE REFERENCE OF PROXY OBJECT
		UserWalletEntity senderWallet=null;
		
		UserWalletEntity tempSenderWallet = new UserWalletEntity();
		
		
		MerchantWalletEntity receiverWallet=null;
		MerchantWalletEntity tempReceiverWallet = new MerchantWalletEntity();
		
		
		senderWallet = userWalletService.getById(transaction.getSenderUserId());
		logger.info("user sender wallet {}",senderWallet.toString());
		
		tempSenderWallet.setUserId(senderWallet.getUserId());
		tempSenderWallet.setStatus(senderWallet.getStatus());
		tempSenderWallet.setAmount(senderWallet.getAmount());
		
		
		receiverWallet = merchantWalletService.getById(transaction.getReceiverUserId());
		logger.info("mer receiver wallet {}",receiverWallet.toString());
		
		tempReceiverWallet.setMerchantId(receiverWallet.getMerchantId());
		tempReceiverWallet.setStatus(receiverWallet.getMerchantId());
		tempReceiverWallet.setAmount(receiverWallet.getAmount());
		
		TransactionEntity newTransaction = createTransaction(transaction);
		logger.info("tra created");
		newTransaction.setTransactionType(TransactionType.MERCHANT.toString());
		newTransaction = updateWallet(tempSenderWallet, tempReceiverWallet, newTransaction, transaction);
		
		//FOR SAVING UPDATED WALLETS OF USER & MERCHANT
		saveChanges(tempSenderWallet,tempReceiverWallet,newTransaction);
		transactionDao.save(newTransaction);
		
		//receiverWallet.setStatus(MerchantStatus.INACTIVE.name());
		return newTransaction;

	}
	
	
	public TransactionEntity  onlineRestaurantOrder(ZomatoTransactionsRequestBody transaction) throws Exception {

		UserWalletEntity senderWallet;
		MerchantWalletEntity receiverWallet;
		senderWallet = userWalletService.getById(transaction.getSenderUserId());
		
		logger.info("user sender wallet {}",senderWallet.toString());
		receiverWallet = merchantWalletService.getById(transaction.getReceiverUserId());
		logger.info("mer receiver wallet {}",receiverWallet.toString());
		TransactionEntity newTransaction = createZomatoTransaction(transaction);
		logger.info("tra created");
		newTransaction.setTransactionType(TransactionType.MERCHANT.toString());
		newTransaction = updateZomatoWallet(senderWallet, receiverWallet, newTransaction, transaction);
		
		//Save updated user wallet 
		userWalletDao.save(senderWallet);
		//if(receiverWallet.getMerchantId().equals("8519042337"))
		  //    throw new Exception("manual exception");
		transactionDao.save(newTransaction);
		//Save updated merchant wallet
		
	
		
		merchantWalletDao.save(receiverWallet);
		
		//receiverWallet.setStatus(MerchantStatus.INACTIVE.name());
		
		return newTransaction;

	}

	private TransactionEntity updateZomatoWallet(UserWalletEntity senderWallet, MerchantWalletEntity receiverWallet,
			TransactionEntity newTransaction, ZomatoTransactionsRequestBody transaction) {
		if (senderWallet.getStatus().equalsIgnoreCase("INACTIVE")
				|| receiverWallet.getStatus().equalsIgnoreCase("INACTIVE")
				|| senderWallet.getAmount() < transaction.getAmount()) {

			newTransaction.setStatus(TransactionStatus.FAILED.toString());

		} else {
			
			 senderWallet.setAmount(senderWallet.getAmount() - transaction.getAmount());
			 
			 
			 
			receiverWallet.setAmount(receiverWallet.getAmount() + transaction.getAmount());
			newTransaction.setStatus(TransactionStatus.RECEIVED.toString());
		}

		return newTransaction;
		
	}

	
	public TransactionEntity updateWallet(Wallet senderWallet, Wallet receiverWallet, TransactionEntity newTransaction,
			TransactionCreationBody transaction) throws Exception{

		if (senderWallet.getStatus().equalsIgnoreCase("INACTIVE")
				|| receiverWallet.getStatus().equalsIgnoreCase("INACTIVE")
				|| senderWallet.getAmount() < transaction.getAmount()) {

			newTransaction.setStatus(TransactionStatus.FAILED.toString());

		} else {
			
			logger.info("sender balance {}", senderWallet.getAmount());
			senderWallet.setAmount(senderWallet.getAmount() - transaction.getAmount());
			logger.info("sender balance {}", senderWallet.getAmount());
			
			logger.info("reciver balance {}", receiverWallet.getAmount());
			receiverWallet.setAmount(receiverWallet.getAmount() + transaction.getAmount());
			logger.info("reciver balance {}", receiverWallet.getAmount());
			
			newTransaction.setStatus(TransactionStatus.RECEIVED.toString());
		}

		return newTransaction;
	}

	// TO CREATE TRANSACNITION OBJECT
	public TransactionEntity createTransaction(TransactionCreationBody transaction) {
		TransactionEntity newTransaction = new TransactionEntity();
		newTransaction.setDate(new Date());
		newTransaction.setSenderUserId(transaction.getSenderUserId());
		newTransaction.setReceiverUserId(transaction.getReceiverUserId());
		newTransaction.setAmount(transaction.getAmount());
		return newTransaction;
	}
	
	public TransactionEntity createZomatoTransaction(ZomatoTransactionsRequestBody transaction) {
		TransactionEntity newTransaction = new TransactionEntity();
		newTransaction.setDate(new Date());
		newTransaction.setSenderUserId(transaction.getSenderUserId());
		newTransaction.setReceiverUserId(transaction.getReceiverUserId());
		newTransaction.setAmount(transaction.getAmount());
		newTransaction.setOrderId(transaction.getOrderId());
		return newTransaction;
	}
	
	

	public TransactionEntity updateTransaction(int transactionId, TransactionUpdateEntity transaction) {
		TransactionEntity newTransaction = transactionDao.findById(transactionId);
		newTransaction.setAmount(transaction.getAmount());
		newTransaction.setStatus(transaction.getStatus());
		newTransaction.setTransactionType(transaction.getTransactionType());
		return transactionDao.save(newTransaction);

	}

	public void deleteTransaction(int transactionId) {

		TransactionEntity entity = transactionDao.findById(transactionId);
		transactionDao.delete(entity);
	}
	
	public List<TransactionEntity> getAllTransactionByDate(Date startDate,Date endDate){
		return transactionDao.findByDateBetweenAnd(startDate,endDate);
	}

}
