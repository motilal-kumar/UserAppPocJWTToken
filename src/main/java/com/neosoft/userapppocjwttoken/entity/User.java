package com.neosoft.userapppocjwttoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "user_tab")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "user_id")
	private Integer userId;
	private String username;
	private String password;
	 
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "roles_tab",
			joinColumns = @JoinColumn(name = "user_id")
			)
	@Column(name="role")
	 private Set<String> roles;


}
