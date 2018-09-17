package com.stackroute.tldm.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.tldm.model.UserModel;



//this class is implementing the mongoRepository interface for User//

@Repository
public interface UserRepository extends MongoRepository<UserModel, UUID> {

	UserModel getUserByUserName(String userName);

//	UserModel existsById(UUID userId);

}
