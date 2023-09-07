package com.bcbsm.filemgt.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bcbsm.filemgt.model.UserModel;
import com.bcbsm.filemgt.repository.UserRepository;
import com.bcbsm.filemgt.security.BcbsUser;
import com.bcbsm.filemgt.security.UserRole;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserModel getUser(String name) {
		BcbsUser objBcbsUser = userRepository.findUserByUsername(name);

		if (objBcbsUser != null) {
			return new UserModel(objBcbsUser.getUsername(), objBcbsUser.getUserFirstName(),
					objBcbsUser.getUserLastName(),
					objBcbsUser.getUserRoles().stream().map(role -> role.name()).collect(Collectors.toSet()));
		} else {
			return null;
		}
	}

	@Override
	public String addUser(UserModel user) {

		Set<UserRole> userRoles = new HashSet<>();

		for (String role : user.getRoles()) {
			if (role.equals(UserRole.ROLE_ADMIN.name())) {
				userRoles.add(UserRole.ROLE_ADMIN);
			} else {
				userRoles.add(UserRole.ROLE_USER);
			}
		}

		BcbsUser newUser = new BcbsUser(user.getUsername(), passwordEncoder.encode(user.getPassword()), userRoles,
				user.getFirstName(), user.getLastName());
		userRepository.save(newUser);

		return user.getUsername();
	}

}
