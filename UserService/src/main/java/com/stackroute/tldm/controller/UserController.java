package com.stackroute.tldm.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	//this handler method is mapped to the URL "/api/user" using  HTTP POST method//

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
		ResponseEntity<?> responseEntity = null;
		try {
			if (service.registerUser(user) != null) {
				responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
			}
		} catch (UserAlreadyExistsException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);

		}
		return responseEntity;
	}
	//this handler method is mapped to the URL "/api/user/{userId}" using  HTTP PUT method//

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateMethod(@PathVariable UUID userId, @RequestBody UserModel user) {
		ResponseEntity<?> responseEntity = null;
		try {
			service.updateUser(userId, user);
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

		} catch (UserNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

		}

		return responseEntity;
	}
	//this handler method is mapped to the URL "/api/user/name/{userName}" using  HTTP GET method//


	@GetMapping("/name/{userName}")
	public ResponseEntity<?> showMethod(@PathVariable String userName) {
		ResponseEntity<?> responseEntity = null;

		try {
			if (service.getUserByUserName(userName) != null) {
				responseEntity = new ResponseEntity<>(service.getUserByUserName(userName), HttpStatus.OK);

			} else {
				responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}
	//this handler method is mapped to the URL "/api/user/{userId}" using  HTTP DELETE method//

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteByName(@PathVariable UUID userId) {
		ResponseEntity<?> responseEntity = null;
		try {
			if (service.deleteUser(userId)) {
				responseEntity = new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
			}
		} catch (UserNotFoundException exception) {
			responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}
	//this handler method is mapped to the URL "/api/user/{Id}" using  HTTP GET method//


	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable UUID id) {
		ResponseEntity<?> responseEntity = null;
		UserModel fetch;
		try {
			fetch = service.getUserById(id);
			if (fetch != null) {
				responseEntity = new ResponseEntity<>(fetch, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("user details for id is not found", HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return responseEntity;

	}
	//this handler method is mapped to the URL "/api/user" using  HTTP GET method//


	@GetMapping
	public ResponseEntity<?> getAllUserDetails() {
		ResponseEntity<?> responseEntity = null;
		List<UserModel> nameList = service.getAllUsers();
		if (nameList != null) {
			responseEntity = new ResponseEntity<>(nameList, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

}
