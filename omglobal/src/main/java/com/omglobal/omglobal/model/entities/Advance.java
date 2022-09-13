package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "advance")
public class Advance {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "payment_date")
    private String date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "note")
    private String note;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
