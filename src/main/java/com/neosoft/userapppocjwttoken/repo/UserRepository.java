package com.neosoft.userapppocjwttoken.repo;

import com.neosoft.userapppocjwttoken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository.
 *
 * @author Motilal Kumar.
 * @version 1.0
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    User getByUsername(String username);

    Boolean  existsByUsername(String username);

}
