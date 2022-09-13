package com.omglobal.omglobal.model.entities;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity(name = "variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_name")
    private String name;

    @Column(name = "discription")
    private String discription;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

}
