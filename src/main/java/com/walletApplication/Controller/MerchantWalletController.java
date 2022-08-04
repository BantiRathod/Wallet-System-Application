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

import com.walletApplication.Dto.MerchantWalletCreationBody;
import com.walletApplication.Dto.MerchantWalletUpdateEntity;
import com.walletApplication.Entities.MerchantWalletEntity;
import com.walletApplication.Services.MerchantWalletService;
import com.walletApplication.Validation.MerchantWalletServiceValidation;

@RestController
public class MerchantWalletController {
	@Autowired
	private MerchantWalletService merchantWalletService;

	@GetMapping("/getallmerchantWallet")
	public ResponseEntity<?> getAllMerchantWallet() {
		List<MerchantWalletEntity> merchantWallet;
		try {
			merchantWallet = merchantWalletService.getAllMerchantWallet();
			if (merchantWallet.isEmpty()) {
				return new ResponseEntity<>("Not found any MerchantWallet", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(merchantWallet, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/merchantwallet/{merchantId}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneMerchantWalletById(@PathVariable String merchantId) {
		MerchantWalletEntity merchantWallet = null;
		try {
			MerchantWalletServiceValidation.validateMerchantWalletId(merchantId);
			merchantWallet = merchantWalletService.getById(merchantId);
			return new ResponseEntity<>(merchantWallet, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registerMerchantwallet")
	public ResponseEntity<?> createMerchantWallet(@RequestBody MerchantWalletCreationBody merchantWallet) {
		try {
			MerchantWalletServiceValidation.validateMerchantWalletId(merchantWallet.getMerchantId());
			MerchantWalletServiceValidation.validateMerchantWalletAmount(merchantWallet.getAmount());
			MerchantWalletEntity merchantWalletSaved = merchantWalletService.createMerchantWallet(merchantWallet);
			return new ResponseEntity<>(merchantWalletSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/updatemerchantwallet/{merchantId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMerchantWallet(@PathVariable String merchantId,
			@RequestBody MerchantWalletUpdateEntity merchantWallet) {
		try {
			MerchantWalletServiceValidation.validateMerchantWalletId(merchantId);
			MerchantWalletServiceValidation.validateMerchantWalletAmount(merchantWallet.getAmount());
			MerchantWalletServiceValidation.validateMerchantWalletStatus(merchantWallet.getStatus());
			MerchantWalletEntity merchantWalletUpdate = merchantWalletService.updateMerchantWallet(merchantId,
					merchantWallet);
			return new ResponseEntity<>(merchantWalletUpdate, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value = "/deletemerchantwallet/{merchantId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMerchantWallet(@PathVariable String merchantId) {
		try {
			MerchantWalletServiceValidation.validateMerchantWalletId(merchantId);
			merchantWalletService.deleteMerchantWallet(merchantId);
			return new ResponseEntity<String>("merchant Wallet has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("merchant Wallet has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

}
