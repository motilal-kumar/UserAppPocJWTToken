package com.neosoft.userapppocjwttoken.repo;


import com.neosoft.userapppocjwttoken.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMasterRepository.
 *
 * @author Motilal Kumar.
 * @version 1.0
 *
 */
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

    //List<UserMaster> findUserByDepartment(String department);
    List<UserMaster> findUserByEmailId(String emailId);
   @Query(value = "SELECT * FROM user_master_tab um WHERE LOWER(um.first_name) LIKE LOWER(CONCAT('%',:query, '%'))", nativeQuery = true)
    List<UserMaster> searchUserMaster(@Param("query") String query);

    Boolean  existsByEmailId(String emailId);
   //Boolean  existsByFirstNameAndLastName(String firstName, String lastName);


}
