package com.neosoft.userapppocjwttoken.repo;



import com.neosoft.userapppocjwttoken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);

}
