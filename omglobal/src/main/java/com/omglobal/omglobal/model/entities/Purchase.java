package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "purchase")
public class Purchase {

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
    @JoinColumn (name = "warehouse_id")
    private Warehouse warehouse;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "supplier_id")
    private Supplier supplier;

    @Column(name = "purchase_code")
    private String purchaseCode;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "purchase_status")
    private String purchaseStatus;

    @Column(name = "purchase_note")
    private String note;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "other_charges")
    private Double otherCharges;

    @Column(name = "discount_on_all")
    private Double discountOnAll;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "round_off")
    private Double roundOff;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name = "privious_due")
    private Double priviousDue;

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
    @OneToMany(mappedBy = "purchase")
    private Set<PurchasePayments> purchasePayments ;


}
