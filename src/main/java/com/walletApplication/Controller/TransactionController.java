package com.walletApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walletApplication.Dto.TransactionCreationBody;
import com.walletApplication.Dto.TransactionUpdateEntity;
import com.walletApplication.Dto.ZomatoTransactionsRequestBody;
import com.walletApplication.Entities.TransactionEntity;
import com.walletApplication.Services.TransactionService;
import com.walletApplication.Validation.TransactionServiceValidation;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/getalltransaction")
	public ResponseEntity<?> getAllTransaction() {
		List<TransactionEntity> transaction;
		try {
			transaction = transactionService.getAllTransaction();
			if (transaction.isEmpty()) {
				return new ResponseEntity<>("Not found any transaction", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneTransactionById(@PathVariable int transactionId) {
		TransactionEntity transaction = null;
		try {
			TransactionServiceValidation.validateTransactionId(transactionId);
			transaction = transactionService.getById(transactionId);
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/sendmoneytouser")
	public ResponseEntity<?> userTransaction(@RequestBody TransactionCreationBody transaction) {
		try {
			TransactionServiceValidation.validateTransactionSenderUserId(transaction.getSenderUserId());
			TransactionServiceValidation.validateTransactionReceiverUserId(transaction.getReceiverUserId());
			TransactionServiceValidation.validateTransactionAmount(transaction.getAmount());
			TransactionEntity transactionSaved = transactionService.sendMoneyToUser(transaction);
			return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	
	}
	
	@PostMapping("/sendmoneytomerchant")
	public ResponseEntity<?> merchantTransaction(@RequestBody TransactionCreationBody transaction) {
		try {
			TransactionServiceValidation.validateTransactionSenderUserId(transaction.getSenderUserId());
			TransactionServiceValidation.validateTransactionReceiverUserId(transaction.getReceiverUserId());
			TransactionServiceValidation.validateTransactionAmount(transaction.getAmount());
			TransactionEntity transactionSaved = transactionService.sendMoneyToMerchant(transaction);
			return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/sendmoneytozomato")
	public int orderTransaction(@RequestBody ZomatoTransactionsRequestBody transaction) {
		int id=0; 
		try {
			TransactionServiceValidation.validateTransactionSenderUserId(transaction.getSenderUserId());
			TransactionServiceValidation.validateTransactionReceiverUserId(transaction.getReceiverUserId());
			TransactionServiceValidation.validateTransactionAmount(transaction.getAmount());
			TransactionEntity  tran = transactionService.onlineRestaurantOrder(transaction);
			id = tran.getTransactionId();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		
		return id; 
	}
	
	@PostMapping("/registertransaction")
	public ResponseEntity<?> createTransaction(@RequestBody TransactionCreationBody transaction) {
		try {
			TransactionServiceValidation.validateTransactionSenderUserId(transaction.getSenderUserId());
			TransactionServiceValidation.validateTransactionReceiverUserId(transaction.getReceiverUserId());
			TransactionServiceValidation.validateTransactionAmount(transaction.getAmount());
			TransactionEntity transactionSaved = transactionService.createTransaction(transaction);
			return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/updatetransaction/{transactionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTransaction(@PathVariable int transactionId,
			@RequestBody TransactionUpdateEntity transaction) {
		try {
			TransactionServiceValidation.validateTransactionId(transactionId);
			TransactionServiceValidation.validateTransactionAmount(transaction.getAmount());
			TransactionServiceValidation.validateTransactionStatus(transaction.getStatus());
			TransactionServiceValidation.validateTransactionType(transaction.getTransactionType());
			TransactionEntity transactionUpdate = transactionService.updateTransaction(transactionId, transaction);
			return new ResponseEntity<>(transactionUpdate, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value = "/deletetransaction/{transactionId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTransaction(@PathVariable int transactionId) {
		try {
			TransactionServiceValidation.validateTransactionId(transactionId);
			transactionService.deleteTransaction(transactionId);
			return new ResponseEntity<String>("Transaction has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Transaction has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

}
