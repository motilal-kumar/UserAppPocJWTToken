package com.neosoft.userapppocjwttoken.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * 	User.
 *
 * 	@author	 Motilal Kumar.
 * 	 @version 1.0
 *
 */
@Data
@Entity
@Table(name = "user_tab")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "user_id")
	private Integer userId;

	@NotNull
	@Size(min = 2, max = 15 ,message = "Enter valid UserName" )
	@Pattern(regexp = "^[A-Za-z]\\w{5,29}$")
	private String username;

	@NotNull
	@Size(min = 5, message = "Invalid length for Password")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,}$")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "roles_tab",
			joinColumns = @JoinColumn(name = "user_id")
			)
	@Column(name="role")
	 private Set<String> roles;


}
