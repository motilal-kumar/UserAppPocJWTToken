package com.neosoft.userapppocjwttoken.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *UserContext.
 *
 * @author  Motilal Kumar.
 * version  1.0
 *
 */
@Data
@Entity
@Table(name= "user_contact_tab")
public class UserContext {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contextId;

    @NotNull(message = "Mobile No number can not be null")
    @Size(min = 10, max = 12, message = "Mobile Number should be 10 digit")
    @Pattern(regexp = "(0|91)?[6-9][0-9]{9}")
    private String mobileNo;

    @NotNull(message = "Alternate Mobile No number can not be null")
    @Size(min = 10, max = 12, message = "Alternate Mobile Number should be 10 digit")
    @Pattern(regexp = "(0|91)?[6-9][0-9]{9}")
    private String alternateMobileNo;

    @NotNull(message = "Department can not be null")
    @Size(min = 2, max = 10, message = "The length of Department must be between 2 and 10 characters.")
    private String department;

   @NotNull(message = "Address is required.")
   @Size(min = 2, max = 20, message = "The length of Address must be between 2 and 20 characters.")
   private String address;

}
