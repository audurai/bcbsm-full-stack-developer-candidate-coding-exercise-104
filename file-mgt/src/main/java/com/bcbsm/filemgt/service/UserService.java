package com.bcbsm.filemgt.service;

import com.bcbsm.filemgt.model.UserModel;

public interface UserService {

	UserModel getUser(String name);

	String addUser(UserModel user);

}
