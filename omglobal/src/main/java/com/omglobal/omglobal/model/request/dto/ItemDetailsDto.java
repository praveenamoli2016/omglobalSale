package com.omglobal.omglobal.model.request.dto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Sale;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import com.omglobal.omglobal.utility.enums.TaxType;

import javax.persistence.*;

public class ItemDetailsDto {
    private Long storeId;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "sale_id")
    private Sale sale;

    @Column(name = "item_id")
    private Long item;

    @Column(name = "sales_items_status")
    private String salesItemStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "sales_quantity")
    private Double quantity;

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


    @Column(name = "seller_points")
    private Double sellerPoints;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;
}
