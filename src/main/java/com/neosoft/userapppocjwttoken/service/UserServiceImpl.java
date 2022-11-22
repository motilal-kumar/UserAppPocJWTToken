package com.neosoft.userapppocjwttoken.service;

import com.neosoft.userapppocjwttoken.entity.User;
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

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;


	//Save Method
	@Override
	public UserMaster saveUserData(UserMaster userMaster) {

		/*User user = new User();

		user.setPassword(
				pwdEncoder.encode(
						user.getPassword())
		);
*/


		userMaster.getUser().setPassword(

				pwdEncoder.encode(
						userMaster.getUser().getPassword())

		);

		return userMasterRepository.save(userMaster);
	}


	@Override
	public List<UserMaster> findAllUserData() {
		return userMasterRepository.findAll();
	}



	public UserMaster findUserById(int userMasterId) {
		return userMasterRepository.findById(userMasterId).orElse(null);
	}

	@Override
	public List<UserMaster> searchUserMaster(String query) {
		List<UserMaster> userMasters = userMasterRepository.searchUserMaster(query);
		return userMasters;
	}

	@Override
	public UserMaster findUserByDepartment(String department) {
		return userMasterRepository.findUserByDepartment(department);
	}


	/*@Override
	public List<User> findAllUser() {

		List<User>  userList = userRepository.findAll();

		System.out.println("userList: "+userList);

		if(userList.size()>0){

			return userList;

		}else{

			return new ArrayList<User>();
		}

	}*/


	//getUser by Username
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	//-------------------------------------------------------------------//

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("loadUserByUsername");
		Optional<User> opt =findByUsername(username);
		System.out.println("optopt:"+opt);
		System.out.println("optopt:"+opt.get());
		if (opt.isEmpty())
			throw new UsernameNotFoundException("User not exists!");

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
