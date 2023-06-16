package com.neosoft.userapppocjwttoken.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * UserMasterResponse.
 *
 * @author Motilal Kumar.
 * @version 1.0
 *
 */
@Data
public class UserMasterResponse {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    private Integer monthlyIncome;
    private String department;
    private Date dob;

    private List<UserContextsResponse> userContexts;
   // private User user;


}
