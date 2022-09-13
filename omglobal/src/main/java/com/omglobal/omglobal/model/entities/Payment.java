package com.omglobal.omglobal.model.entities;

import com.omglobal.omglobal.utility.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "account_number")
    private String account;



    @Column(name = "payment_note")
    private String paymentNote;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}
