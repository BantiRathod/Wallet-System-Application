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

import com.walletApplication.Dto.UserWalletCreationBody;
import com.walletApplication.Dto.UserWalletUpdateEntity;
import com.walletApplication.Entities.UserWalletEntity;
import com.walletApplication.Services.UserWalletService;
import com.walletApplication.Validation.UserWalletServiceValidation;

@RestController
public class UserWalletController {
	@Autowired
	private UserWalletService userWalletService;

	@GetMapping("/getalluserWallet")
	public ResponseEntity<?> getAllUserWallet() {
		List<UserWalletEntity> userWallet;
		try {
			userWallet = userWalletService.getAllUserWallet();
			if (userWallet.isEmpty()) {
				return new ResponseEntity<>("Not found any userWallet", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(userWallet, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/userwallet/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneUserWalletById(@PathVariable String userId) {
		UserWalletEntity userWallet = null;
		try {
			UserWalletServiceValidation.validateUserWalletId(userId);
			userWallet = userWalletService.getById(userId);
			return new ResponseEntity<>(userWallet, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registeruserwallet")
	public ResponseEntity<?> createUserWallet(@RequestBody UserWalletCreationBody userWallet) {
		try {
			UserWalletServiceValidation.validateUserWalletId(userWallet.getUserId());
			UserWalletServiceValidation.validateUserWalletAmount(userWallet.getAmount());
		    
			UserWalletEntity userWalletSaved = userWalletService.createUserWallet(userWallet);
			return new ResponseEntity<>(userWalletSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/updateuserwallet/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserWallet(@PathVariable String userId,@RequestBody UserWalletUpdateEntity userWallet) {
		try {
			UserWalletServiceValidation.validateUserWalletId(userId);
			UserWalletServiceValidation.validateUserWalletAmount(userWallet.getAmount());
			UserWalletServiceValidation.validateUserWalletStatus(userWallet.getStatus());
			
			UserWalletEntity userWalletUpdate = userWalletService.updateUserWallet(userId, userWallet);
			return new ResponseEntity<>(userWalletUpdate, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value = "/deleteuserwallet/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserWallet(@PathVariable String userId) {
		try {
			UserWalletServiceValidation.validateUserWalletId(userId);
			userWalletService.deleteUserWallet(userId);
			return new ResponseEntity<String>("userWallet has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("userWallet has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

}
