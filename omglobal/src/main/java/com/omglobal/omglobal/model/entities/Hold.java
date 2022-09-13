package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "hold")
public class Hold {


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
    @JoinColumn (name = "warehouse_id")
    private Warehouse warehouse;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;


//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "hold_item",
//            joinColumns = @JoinColumn(name = "hold",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "item",referencedColumnName = "id"))
//    private List<Item> itemSet;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "sales_date")
    private String date;

    @Column(name = "sales_note")
    private String note;

    @Column(name = "sales_status")
    private String salesStatus;

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

    @Column(name = "pos")
    private int pos;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "hold")
//    private Set<SalesInvoice> salesInvoices;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "hold")
    private Set<HoldItems> holdItems;

}
