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

import com.walletApplication.Dto.MerchantCreationBody;
import com.walletApplication.Dto.MerchantUpdateEntity;
import com.walletApplication.Entities.MerchantEntity;
import com.walletApplication.Services.MerchantService;
import com.walletApplication.Validation.MerchantServiceValidation;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@GetMapping("/getallmerchants")
	public ResponseEntity<?> getAllMerchant() {
		List<MerchantEntity> merchants;
		try {
			merchants = merchantService.getAllMerchant();
			if (merchants.isEmpty()) {
				return new ResponseEntity<>("Not found any merchants", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(merchants, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/merchant/{mobileNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneMerchantById(@PathVariable String mobileNumber) {
		MerchantEntity merchant = null;
		try {
			MerchantServiceValidation.validateMerchantMobileNumber(mobileNumber);
			merchant = merchantService.getById(mobileNumber);
			return new ResponseEntity<>(merchant, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registermerchant")
	public ResponseEntity<?> createMerchant(@RequestBody MerchantCreationBody merchant) {
		try {
			MerchantServiceValidation.validateMerchantMobileNumber(merchant.getMobileNumber());
			MerchantServiceValidation.validateMerchantName(merchant.getName());
			MerchantServiceValidation.validateMerchantPassword(merchant.getPassword());
			MerchantEntity merchantSaved = merchantService.createMerchant(merchant);
			return new ResponseEntity<>(merchantSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value = "/updatemerchant/{mobileNumber}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMerchant(@PathVariable String mobileNumber,
			@RequestBody MerchantUpdateEntity merchant) {
		try {
			MerchantServiceValidation.validateMerchantMobileNumber(mobileNumber);
			MerchantServiceValidation.validateMerchantName(merchant.getName());
			MerchantServiceValidation.validateMerchantPassword(merchant.getPassword());
			MerchantServiceValidation.validateMerchantAddress(merchant.getAddress());
			MerchantEntity merchantUpdate = merchantService.updateMerchant(mobileNumber, merchant);
			return new ResponseEntity<>(merchantUpdate, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value = "/deletemerchant/{mobileNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMerchant(@PathVariable String mobileNumber) {
		try {
			MerchantServiceValidation.validateMerchantMobileNumber(mobileNumber);
			merchantService.deleteMerchant(mobileNumber);
			return new ResponseEntity<String>("user has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("user has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

}
