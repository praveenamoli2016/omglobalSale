package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.model.entities.Role;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class UserRequest {
    private Long id;
    private Long storeId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    //@Lob
    //private byte[] profilePicture;
    private String password;
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;
    private String dateOfBirth;
    private String gender;
    private String country;
    private String state;
    private String city;
    private String address;
    private String postCode;
    private Boolean isDeleted;
    private String createdBy;
    private Long roleId;
    private Set<Long> warehouseIds;
}
