package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Hold;
import com.omglobal.omglobal.model.entities.Item;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import com.omglobal.omglobal.utility.enums.TaxType;
import lombok.Data;

import javax.persistence.*;

@Data
public class HoldItemsRequest {


    private Long id;
    private Long holdId;
    private Long itemId;
    private Long storeId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double discountInput;
    private Double discount;
    private DiscountType discountType;
    private Long taxId;
    private Double taxAmount;
    private TaxType taxType;
    private Double unitTotalCost;
    private Double totalCost;
    private Status status = Status.ACTIVE;
    private String createdBy;
}
