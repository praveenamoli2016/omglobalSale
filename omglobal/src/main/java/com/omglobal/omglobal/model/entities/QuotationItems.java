package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import com.omglobal.omglobal.utility.enums.TaxType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "quotation_items")
public class QuotationItems {

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
    @JoinColumn (name = "quotation_id")
    private Quotation quotation;


    @Column(name = "item_id")
    private Long item;

    @Column(name = "description")
    private String description;


    @Column(name = "quotation_quantity")
    private Integer quantity;


    @Column(name = "price_per_unit")
    private Double unitPrice;

    @Column(name = "discount_input")
    private Double discountInput;

    @Column(name = "discount_amount")
    private Double discount;

    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "tax_id")
    private Long tax;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "tax_type")
    private TaxType taxType;

    @Column(name = "unit_total_cost")
    private Double unitTotalCost;


    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "seller_points")
    private Double sellerPoints;


    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
