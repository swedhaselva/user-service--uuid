package com.stackroute.tldm.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserModel {

	@Id
	
	private UUID userId;
	private String userName;
	private String name;
	private String phoneNum;
	private String userMail;

	public UserModel() {

	}

	public UserModel(UUID userId, String userName, String name, String phoneNum, String userMail) {
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.phoneNum = phoneNum;
		this.userMail = userMail;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", userMail=" + userMail + "]";
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
}

//	@Override
//	public String toString() {
//		return "UserModel{" +
//				"userId='" + userId + '\'' +
//				", userName='" + userName + '\'' +
//				", name='" + name + '\'' +
//				", phoneNum='" + phoneNum + '\'' +
//				", userMail='" + userMail + '\'' +
//				'}';
//	}
//}
