package com.omglobal.omglobal.model.entities;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "parent_account")
public class ParentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_account_name")
    private String name;

    @Column(name = "parent_account_number")
    private Long accountNumber;

    @Column(name = "balence")
    private Double balence;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}
