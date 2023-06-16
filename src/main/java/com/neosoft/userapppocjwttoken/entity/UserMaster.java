package com.neosoft.userapppocjwttoken.entity;


import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * UserMaster.
 *
 * @author Motilal Kumar.
 * version  1.0
 *
 */
@Data
@Entity
@Table(name= "user_master_tab")
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_master_id")
    private int userMasterId;

    @NotNull(message = "FirstName is required.")
    @Size(min = 2, max = 15, message = "The length of FirstName must be between 2 and 15 characters.")
    private String firstName;

    @NotNull(message = "LastName is required.")
    @Size(min = 2, max = 15, message = "The length of LastName must be between 2 and 15 characters.")
    private String lastName;

    @NotNull(message = "FatherName is required.")
    @Size(min = 2, max = 15, message = "The length of FatherName must be between 2 and 15 characters.")
    private String fatherName;

    @NotNull(message = "MotherName is required.")
    @Size(min = 2, max = 15, message = "The length of MotherName must be between 2 and 15 characters.")
    private String motherName;

    @NotNull
    @Size(min = 5, max = 20, message = "Invalid length for email")
    // @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE)
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailId;

    @NotNull
    @Min(value = 2000, message = "Salary can not be less than 2000")
    @Max(value = 50000, message = "Salary can not be greater than 50000")
   // @Pattern(regexp = "^((\\\\d*[1-9]+\\\\d*(\\\\.\\\\d+)?)|(\\\\d*\\\\.\\\\d*[1-9]+\\\\d*))$")
    private Double monthlyIncome;


    @NotNull(message = "dob can not be null")
    @Past(message = "The date of birth must be in the past.")
    private Date dob;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    @NotNull
    @Valid
    private List<UserContext> userContexts;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name ="user_id")
    @NotNull
    @Valid
    private User user;

}
