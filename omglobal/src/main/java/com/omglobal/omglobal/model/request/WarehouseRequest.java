package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WarehouseRequest {

    private Long id;
   // @NotBlank(message = "Name is mandatory")
    private String name;
   // @Email(message = "Email should be valid")
    private String email;
    //@NotNull
    private String mobileNumber;
    //@NotNull
    private Status status=Status.ACTIVE;
    private Boolean isDeleted;

}
