package com.walletApplication.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.walletApplication.Dto.UserCreationBody;
import com.walletApplication.Dto.UserUpdateEntity;
import com.walletApplication.Entities.UserEntity;
import com.walletApplication.Services.UserService;
import com.walletApplication.Validation.UserServiceValidation;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@GetMapping("/getallusers")
	public ResponseEntity<?> getAllUser() {
		List<UserEntity> users;
		try {
			users = userService.getAllUser();
			if (users.isEmpty()) {
				return new ResponseEntity<>("Not found any users", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/user/{mobileNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneUserById(@PathVariable String mobileNumber) {
		UserEntity user = null;
		try {

			logger.info("received user mobilenumber : {} ", mobileNumber);
			UserServiceValidation.validateUserMobileNumber(mobileNumber);
			user = userService.getById(mobileNumber);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registeruser")
	public ResponseEntity<?> createUser(@RequestBody UserCreationBody user) {
		try {
			UserServiceValidation.validateUserMobileNumber(user.getMobileNumber());
			UserServiceValidation.validateUserName(user.getName());
			UserServiceValidation.validateUserPassword(user.getPassword());
			UserEntity userSaved = userService.createUser(user);
			return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/updateuser/{mobileNumber}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable String mobileNumber, @RequestBody UserUpdateEntity user) {
		try {
			UserServiceValidation.validateUserMobileNumber(mobileNumber);
			UserServiceValidation.validateUserName(user.getName());
			UserServiceValidation.validateUserPassword(user.getPassword());
			UserEntity userUpdate = userService.updateUser(mobileNumber, user);
			return new ResponseEntity<>(userUpdate, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/deleteuser/{mobileNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable String mobileNumber) {
		try {
			UserServiceValidation.validateUserMobileNumber(mobileNumber);
			userService.deleteUser(mobileNumber);
			return new ResponseEntity<String>("user has been deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("user has not been deleted", HttpStatus.NOT_FOUND);

		}

	}

}