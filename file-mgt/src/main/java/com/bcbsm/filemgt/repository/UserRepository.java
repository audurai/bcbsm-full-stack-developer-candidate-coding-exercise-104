package com.bcbsm.filemgt.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bcbsm.filemgt.security.BcbsUser;

public interface UserRepository extends MongoRepository<BcbsUser, ObjectId> {

	public BcbsUser findUserByUsername(String username);

}
