package com.neosoft.userapppocjwttoken.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "user_master_tab")
public class UserMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_master_id")
    private int userMasterId;


    @NotNull(message = "FirstName is required.")
    @Size(min = 2, max = 100, message = "The length of FirstName must be between 2 and 100 characters.")
    @Size(max = 50)
    private String firstName;

    @NotNull(message = "LastName is required.")
    @Size(min = 2, max = 100, message = "The length of LastName must be between 2 and 100 characters.")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "FatherName is required.")
    @Size(min = 2, max = 100, message = "The length of FatherName must be between 2 and 100 characters.")
    @Size(max = 50)
    private String fatherName;

    @NotNull(message = "MotherName is required.")
    @Size(min = 2, max = 100, message = "The length of MotherName must be between 2 and 100 characters.")
    @Size(max = 50)
    private String motherName;
    private Integer monthlyIncome;

    @NotNull(message = "Department name is required.")
    private String department;

    @NotNull(message = "dob can not be null")
    @Past(message = "The date of birth must be in the past.")
    private Date dob;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<UserContext> userContexts;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private User user;

}
