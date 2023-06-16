package com.neosoft.userapppocjwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserResponse.
 *
 * @author Motilal  Kumar.
 *  @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String token;
    private String message;
}
