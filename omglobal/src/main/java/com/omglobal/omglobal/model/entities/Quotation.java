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
@Entity(name = "quotation")
public class Quotation {

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

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "quotation_code")
    private String quotationCode;

    @Column(name = "quotation_date")
    private String quotationDate;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "quotation_status")
    private String quotationStatus;

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

    @Column(name = "paid_amount")
    private Double paymentAmount;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "customer_privious_due")
    private Double priviousDue;

    @Column(name = "customer_total_due")
    private Double totalDue;

    @Column(name = "quotation_note")
    private String note;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "return_bit")
    private Integer returnBit;

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
    @OneToMany(mappedBy = "quotation")
    private Set<Sale> sales;


    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "quotation")
    private Set<QuotationItems> quotationItems;
}
