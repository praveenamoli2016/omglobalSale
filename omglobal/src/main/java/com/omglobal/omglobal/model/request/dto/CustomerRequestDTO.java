package com.omglobal.omglobal.model.request.dto;

import com.omglobal.omglobal.utility.enums.PriceLevelType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRequestDTO {
    private Long id;
    @NotNull(message = "StoreId is required.")
    private Long storeId;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String gstNumber;
    @NotNull
    private String taxNumber;
    @NotNull
    private Double creditLimit;
    private String previousDue;
    private Double openingBalance;
    private String country;
    private String state;
    private String city;
    private Long postCode;
    private String locationLink;
    @NotBlank
    private String address;
    private String shippingCountry;
    private String shippingState;
    private String shippingCity;
    private Long shippingPostCode;
    private String shippingLocationLink;
    private String shippingAddress;
    private Boolean isDeleted;
    @NotNull
    private Status status;
    private Double priceLevel;

}
