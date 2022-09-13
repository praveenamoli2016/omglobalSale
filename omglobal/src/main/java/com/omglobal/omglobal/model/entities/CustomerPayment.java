package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "customer_payment")
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "sales_payments_id")
    private SalesPayments salesPayments;

    @Column(name = "payment_amount")
    private Double amount;

    @Column(name = "advance")
    private Boolean advance;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "date")
    private String date;

    @Column(name = "payment_note")
    private String note;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}