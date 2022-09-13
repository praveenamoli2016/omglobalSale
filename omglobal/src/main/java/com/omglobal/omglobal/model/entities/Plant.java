package com.omglobal.omglobal.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "plant_table")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
