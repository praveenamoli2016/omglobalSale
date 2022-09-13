package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "sales_payments")
public class SalesPayments {

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
    @JoinColumn (name = "sale_id")
    private Sale sale ;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store ;

    @Column(name = "account_id")
    private Long account;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment")
    private Double paymentAmount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "change_return")
    private Double changeReturn ;

    @Column(name = "payment_note")
    private String paymentNote;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "short_code")
    private String shortCode ;

    @Column(name = "adjust_advance_payment")
    private Boolean adjustAdvancePayment ;

    @Column(name = "advance_payment")
    private Double advancePayment ;

    @Column(name = "cheque_number")
    private String chequeNumber;

    @Column(name = "cheque_period")
    private Long chequePeriod;

    @Column(name = "cheque_status")
    private String chequeStatus;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "salesPayments")
    private Set<CustomerPayment> customerPayments;
}
