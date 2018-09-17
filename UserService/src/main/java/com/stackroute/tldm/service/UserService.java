package com.stackroute.tldm.service;

import java.util.List;
import java.util.UUID;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;

public interface UserService {
	UserModel registerUser(UserModel user) throws UserAlreadyExistsException;

	UserModel updateUser(UUID userId, UserModel user) throws UserNotFoundException;

	boolean deleteUser(UUID userId) throws UserNotFoundException;

	public UserModel getUserById(UUID userId) throws UserNotFoundException;

	public UserModel getUserByUserName(String userName) throws UserNotFoundException;
	
	public List<UserModel> getAllUsers();
}
