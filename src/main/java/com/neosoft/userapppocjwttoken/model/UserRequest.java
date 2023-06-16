package com.neosoft.userapppocjwttoken.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * UserRequest.
 *
 * @author Motilal  Kumar.
 * Version 1.0
 */
@Data
public class UserRequest {

    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
}
