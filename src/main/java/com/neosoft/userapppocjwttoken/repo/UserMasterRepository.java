package com.neosoft.userapppocjwttoken.repo;


import com.neosoft.userapppocjwttoken.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

    UserMaster findUserByDepartment(String department);

   @Query(value = "SELECT * FROM user_master um WHERE LOWER(um.first_name) LIKE LOWER(CONCAT('%',:query, '%'))", nativeQuery = true)
    List<UserMaster> searchUserMaster(@Param("query") String query);


}
