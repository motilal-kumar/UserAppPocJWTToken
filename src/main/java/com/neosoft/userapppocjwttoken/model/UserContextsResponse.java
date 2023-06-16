package com.neosoft.userapppocjwttoken.model;

import lombok.Data;

/**
 * UserContextsResponse.
 *
 * @author  Motilal Kumar.
 * @version 1.0
 *
 */
@Data
public class UserContextsResponse {
    private long mobileNo;
    private long alternateMobileNo;
    private String emailId;
    private String address;
}
