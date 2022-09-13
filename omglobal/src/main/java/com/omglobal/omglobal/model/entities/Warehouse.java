package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "warehouse_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "warehouse")
    private Set<Quotation> quotations;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "warehouse")
    private Set<Sale> sales;


//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "warehouse")
//    private Set<Hold> holds ;
//
    @ManyToMany(mappedBy = "warehouses")
    @JsonIgnore
    private Set<User> users;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "warehouse")
//    private Set<SalesInvoice> salesInvoice ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "warehouse")
//    private Set<Purchase> purchases ;

}
