package com.stackroute.tldm.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tldm.controller.UserController;
import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.service.UserService;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	@MockBean
	private UserModel user;
	@InjectMocks
	UserController userController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new UserModel();
		user.setUserId("12345");
		user.setName("swetha");
		user.setPhoneNum("56528769987");
		user.setUserName("swedha12");
		user.setUserMail("swedha87@gmail.com");
	}
    
	@Test
	public void registerUserSuccess() throws Exception {
		when(userService.registerUser(user)).thenReturn(user);
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void registerUserFailure() throws Exception {
		when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	public void deleteUserSuccess()throws Exception{
		when(userService.deleteUser("12345")).thenReturn(true);
		mockMvc.perform(delete("/api/user/12345").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void deleteUserFailure()throws Exception{
		when(userService.deleteUser("12345")).thenThrow(UserNotFoundException.class);
		mockMvc.perform(delete("/api/user/12345").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}
	
	
	
	@Test
	public void updateUserSuccess() throws Exception{
		user.setUserMail("swedha87@gmail.com");
		when(userService.updateUser(eq(user.getUserId()), any())).thenReturn(user);
		mockMvc.perform(put("/api/user/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void updateUserFailure() throws Exception{
		user.setUserMail("swedha87@gmail.com");
		when(userService.updateUser(eq(user.getUserId()), any())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(put("/api/user/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getByUserIdSuccess() throws Exception{
		
		when(userService.getUserById("12345")).thenReturn(user);
		mockMvc.perform(get("/api/user/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getByUserIdFailure() throws Exception{
		
		when(userService.getUserById("12345")).thenThrow(UserNotFoundException.class);
		mockMvc.perform(get("/api/user/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	
	
	

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//	@Test
//	public void updateUserSuccess() throws Exception {
//		when(userService.updateUser(user.getUserId(), user))
//	}

}