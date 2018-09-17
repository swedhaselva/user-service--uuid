package com.stackroute.tldm.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.repository.UserRepository;
import com.stackroute.tldm.service.UserServiceImpl;

public class UserServiceImplTest {
	@Mock
	UserRepository userRepo;
	UserModel user;
	@InjectMocks
	UserServiceImpl userService;
	List<UserModel> userList=null;
	Optional<UserModel> options;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		user = new UserModel();
		user.setUserId("12345");
		user.setName("swetha");
		user.setPhoneNum("56528769987");
		user.setUserName("swedha12");
		user.setUserMail("swedha87@gmail.com");
	userList = new ArrayList();
	userList.add(user);
		
		options = Optional.of(user);
	}
	
	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException{
		when(userRepo.insert((UserModel) any())).thenReturn(user);
		UserModel registerUser = userService.registerUser(user);
		assertEquals(user, registerUser);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void registerUserFailure() throws UserAlreadyExistsException{
		when(userRepo.insert((UserModel) any())).thenReturn(null);
	UserModel registerUser = userService.registerUser(user);
	assertEquals(user, registerUser);
	}
	
	@Test
	public void getUserByUserName() throws UserNotFoundException{
		when(userRepo.getUserByUserName(user.getUserName())).thenReturn(user);
		UserModel fetchUser=userService.getUserByUserName(user.getUserName());
	assertEquals(user,fetchUser);


	}
	
	@Test
	public void deleteUserSuccess() throws UserNotFoundException{
		when(userRepo.findById(user.getUserId())).thenReturn(options);
		boolean flag=userService.deleteUser(user.getUserId());
		assertEquals(true,flag);
		
	}
	
	@Test
	public void getUserById() throws UserNotFoundException{
		when(userRepo.findById(user.getUserId())).thenReturn(options);
		UserModel fetchUser=userService.getUserById(user.getUserId());
		assertEquals(user,fetchUser);
	}
	
}
	
	
