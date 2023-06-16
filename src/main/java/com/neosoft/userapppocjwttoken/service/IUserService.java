package com.neosoft.userapppocjwttoken.service;

import com.neosoft.userapppocjwttoken.entity.User;
import com.neosoft.userapppocjwttoken.entity.UserMaster;

import java.util.List;
import java.util.Optional;

/**
 * IUserService.
 *
 * @author Motilal Kumar
 *@version 1.0
 *
 */
public interface IUserService {
	UserMaster saveUserData(UserMaster userMaster);
	Optional<User> findByUsername(String username);
	public List<UserMaster> findAllUserData();
	public UserMaster findUserById(int userMasterId);
	public List<UserMaster> searchUserMaster(String query);
	public List<UserMaster> findUserByDepartment(String department);

}
