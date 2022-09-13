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
@Entity(name = "purchase_payments")
public class PurchasePayments {

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
    @JoinColumn (name = "purchase_id")
    private Purchase purchase;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "supplier_id")
    private Supplier supplier;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "account_id")
    private Account account;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment")
    private Double payment;


    @Column(name = "purchase_note")
    private String note;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "purchasePayments")
    private Set<SupplierPayment> supplierPayments;

}
