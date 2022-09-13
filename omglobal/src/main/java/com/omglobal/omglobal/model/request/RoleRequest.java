package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omglobal.omglobal.model.entities.User;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class RoleRequest {


    private Long id;
    private Long storeId;
    private String roleName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

}
