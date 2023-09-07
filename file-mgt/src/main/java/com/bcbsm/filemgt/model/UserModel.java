package com.bcbsm.filemgt.model;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserModel {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Set<String> roles;

	public UserModel(String username, String firstName, String lastName, Set<String> roles) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}

}
