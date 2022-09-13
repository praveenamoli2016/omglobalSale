package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "sales_return")
public class SalesReturn {


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
    @JoinColumn (name = "sale_id")
    private Sale sale;

    @Column (name = "customer_id")
    private Long customer;

    @Column (name = "warehouse_id")
    private Long warehouse;

    @Column (name = "item_id")
    private Long item;

    @Column(name = "return_code")
    private String returnCode;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "referenceNo")
    private String referenceNo;

    @Column(name = "return_status")
    private String returnStatus;

    @Column(name = "return_note")
    private String returnNote;

    @Column(name = "paid_amount")
    private Double payment;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "return_quantity")
    private Integer quantity;

    @Column(name = "other_charges_input")
    private Double otherChargesInput;

    @Column(name = "other_charges_amt")
    private Double otherChargesAmount;

    @Column(name = "other_charges_tax_id")
    private Double otherChargesTax;

    @Column(name = "discount_on_all_input")
    private Double discountOnAllInput;

    @Column(name = "discount_on_all_amt")
    private Double discountOnAll;

    @Column(name = "discount_to_all_type")
    private DiscountType discountType;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "round_off")
    private Double roundOff;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name = "return_bit")
    private Integer returnBit;


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
    @OneToMany(mappedBy = "salesReturn")
    private Set<SalesPaymentsReturn> salesPaymentsReturns ;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "salesReturn")
    private Set<SalesItemsReturn> salesItemsReturns ;


}
