package com.stackroute.tldm.test.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	private UserModel user;

	@Before
	public void setUp() throws Exception {
		user = new UserModel();
		user.setUserId("john123");
		user.setUserName("Swetha Selvaraj");
		user.setPhoneNum("12345");
		user.setName("swe");
		user.setUserMail("swe@gmail.com");
	}

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void registerUserTest() {
		userRepository.insert(user);
		UserModel fetchUser = userRepository.findById("john123").get();
		Assert.assertEquals(user.getUserId(), fetchUser.getUserId());
	}

	@Test
	public void getUserByIdTest() {
		userRepository.insert(user);
		UserModel fetchUser = userRepository.findById("john123").get();
		Assert.assertEquals(user.getUserId(), fetchUser.getUserId());
	}
	
	@Test
	public void getUserByUserNameTest() {
		userRepository.insert(user);
		UserModel fetchUser = userRepository.getUserByUserName("Swetha Selvaraj");
		Assert.assertEquals(user.getUserName(), fetchUser.getUserName());
	}
	@Test
	public void updateUserTest() {
		userRepository.insert(user);
		UserModel fetchUser = userRepository.findById("john123").get();
		fetchUser.setUserMail("swe@gmail.com");
		userRepository.save(fetchUser);
		fetchUser=userRepository.findById("john123").get();
		Assert.assertEquals("swe@gmail.com", fetchUser.getUserMail());
	}

}
