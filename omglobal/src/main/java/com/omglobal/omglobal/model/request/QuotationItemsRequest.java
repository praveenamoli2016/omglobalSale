package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Quotation;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import com.omglobal.omglobal.utility.enums.TaxType;
import lombok.Data;

import javax.persistence.*;

@Data
public class QuotationItemsRequest {


    private Long id;
    private Long storeId;
    private Long quotationId;
    private Long itemId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double discountInput;
    private Double discount;
    private DiscountType discountType;
    private Long tax;
    private Double taxAmount;
    private TaxType taxType;
    private Double unitTotalCost;
    private Double totalCost;
    private Status status = Status.ACTIVE;
    private Double sellerPoints;
    private String createdBy;

}
