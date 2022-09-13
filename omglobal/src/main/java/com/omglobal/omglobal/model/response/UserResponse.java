package com.omglobal.omglobal.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omglobal.omglobal.model.entities.Role;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String profilePictureDownloadURL;
    private String profilePicName;
    private String profilePicType;
    private Status status;
    private String dateOfBirth;
    private String gender;
    private String country;
    private String state;
    private String city;
    private String address;
    private String postCode;
    private Boolean isDeleted;
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private Store store;
    private Role role;
}
