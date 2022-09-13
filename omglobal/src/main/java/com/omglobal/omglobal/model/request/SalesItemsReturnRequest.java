package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.SalesReturn;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import com.omglobal.omglobal.utility.enums.TaxType;
import lombok.Data;

import javax.persistence.*;

@Data
public class SalesItemsReturnRequest {

    private Long id;
    private Long storeId;
    private Long salesReturnId;
    private Long itemId;
    private String discription;
    private String salesItemReturnStatus;
    private Integer returnQuantity;
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
    private String createdBy;

}
