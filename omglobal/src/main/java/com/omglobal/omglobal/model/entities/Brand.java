package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity(name = "Brands_Table")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @NotBlank(message = "Name is mandatory")
    @Column(name = "brand_name")
    private String name;

    @Column(name = "discription")
    private String discription;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "brand")
//    private Set<SalesInvoice> salesInvoices;
}
