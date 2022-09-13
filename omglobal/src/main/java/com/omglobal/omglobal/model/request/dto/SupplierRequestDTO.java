package com.omglobal.omglobal.model.request.dto;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SupplierRequestDTO {


    private Long id;
    @NotBlank(message = "Name is mandatory")
    private Long storeId;
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String gstNumber;
    @NotNull
    private String taxNumber;
    @NotNull
    private Double openingBalance;

    private String country;

    private String state;
    private String city;

    private Long postCode;

    private String address;
    @NotNull
    private Status status;

    private Boolean isDeleted;
    private String createdBy;



}
