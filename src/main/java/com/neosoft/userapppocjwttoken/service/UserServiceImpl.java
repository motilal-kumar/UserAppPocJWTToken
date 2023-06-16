package com.neosoft.userapppocjwttoken.service;

import com.neosoft.userapppocjwttoken.entity.User;
import com.neosoft.userapppocjwttoken.entity.UserContext;
import com.neosoft.userapppocjwttoken.entity.UserMaster;
import com.neosoft.userapppocjwttoken.repo.UserMasterRepository;
import com.neosoft.userapppocjwttoken.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *UserServiceImpl.
 *
 * @author Motilal Kumar.
 *@version 1.0
 *
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	/**
	 * Save User Details
	 *
	 * @param userMaster
	 * @return
	 *
	 */
	@Override
	public UserMaster saveUserData(UserMaster userMaster) {

		UserMaster userMasterEntity = new UserMaster();
		System.out.println("userMasterEntity: " + userMasterEntity);
		userMasterEntity.setFatherName(userMaster.getFirstName());
		userMasterEntity.setLastName(userMaster.getLastName());
		userMasterEntity.setFatherName(userMaster.getFatherName());
		userMasterEntity.setMotherName(userMaster.getMotherName());
		userMasterEntity.setDob(userMaster.getDob());

		List<UserContext>  userContextList = new ArrayList<>();
		System.out.println("userContextList: " + userContextList);
		for(UserContext  ctx: userContextList){

			UserContext  userContext  =  new UserContext();
			System.out.println("userContext: " + userContext);
			userContext.setMobileNo(ctx.getMobileNo());
			userContext.setAlternateMobileNo(ctx.getAlternateMobileNo());
			userContext.setAddress(ctx.getAddress());
			userContextList.add(userContext);

		}
		userMaster.getUser().setPassword(
				pwdEncoder.encode(
						userMaster.getUser().getPassword())
		);

		/*List<UserContext>  userContextList = userMaster.getUserContexts();
		System.out.println("userContextList: "+userContextList);

		List<UserContext>  contextList = new ArrayList<>();
		System.out.println("contextList: "+contextList);

		for (UserContext userCont : userContextList) {

			UserContext  userContext  = new UserContext();
			userContext.setContextId(userCont.getContextId());
			userContext.setMobileNo(userCont.getMobileNo());
			userContext.setAlternateMobileNo(userCont.getAlternateMobileNo());
			userContext.setEmailId(userCont.getEmailId());
			userContext.setAddress(userCont.getAddress());

			contextList.add(userContext);

		}

		userMaster.setUserContexts(contextList);*/

		return userMasterRepository.save(userMaster);
	}


	//2.Get all User Details.
	/**
	 * Get all User Details.
	 *
	 * @return
	 *
	 */
	@Override
	public List<UserMaster> findAllUserData() {
		return userMasterRepository.findAll();
	}

	//3.Find User Details By userMasterId.
	/**
	 * Find User Details By userMasterId.
	 *
	 * @param userMasterId
	 * @return
	 *
	 */
	public UserMaster findUserById(int userMasterId) {
		return userMasterRepository.findById(userMasterId).orElse(null);
	}

	//4.Search User Details By specific firstname.
	/**
	 * Search User Details By specific firstname.
	 *
	 * @param query
	 * @return
	 *
	 */
	@Override
	public List<UserMaster> searchUserMaster(String query) {
		List<UserMaster> userMasters = userMasterRepository.searchUserMaster(query);
		return userMasters;
	}

	//5.Find User Details By department.
	/**
	 * Find User Details By department.
	 *
	 * @param emailId
	 * @return
	 *
	 */
	@Override
	public List<UserMaster> findUserByDepartment(String emailId) {
		return userMasterRepository.findUserByEmailId(emailId);
	}

	//6.Find User  By  Username.
	/**
	 * Find User  By  Username.
	 *
	 * @param username
	 * @return
	 *
	 */
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	//-------------------------------------------------------------------//

	//7.Load User By Username.
	/**
	 * Load User By Username.
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return
	 * @throws UsernameNotFoundException
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("loadUserByUsername");
		Optional<User> opt =findByUsername(username);
		System.out.println("optopt:"+opt);
		System.out.println("optopt:"+opt.get());
		if (opt.isEmpty())
			throw new UsernameNotFoundException("User Doesn't exists!");

		// Read user from DB
		User user = opt.get();
		System.out.println("user.getPassword():"+user.getPassword());
		return new org.springframework.security.core.userdetails.User(
				username,
				user.getPassword(),
				user.getRoles().stream()
						.map(role -> new  SimpleGrantedAuthority(role))
						.collect(Collectors.toList())
				);
	}
}
