package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import java.util.List;
@Data
public class SaleRequest {
    private Long id;
    private Long storeId;
    private Long customerId;
    private Long warehouseId;
    private Long quotationId;
    private String salesCode;
    private String salesDate;
    private String referenceNo;
    private String salesStatus;
    private String dueDate;
    private Status status = Status.ACTIVE;
    private Double quantity;
    private Double otherChargesInput;
    private Double otherChargesAmount;
    private Double otherChargesTax;
    private Double discountOnAllInput;
    private Double discountOnAll;
    private DiscountType discountType;
    private Double subTotal;
    private Double roundOff;
    private Double grandTotal;
    private Double previousDue;
    private Double totalDue;
    private Double paidAmount;
    private String note;
    private PaymentStatus paymentStatus;
    private String createdBy;
    private Integer pos;
    @JsonProperty("payment")
    private SalesPaymentsRequest salesPaymentsRequest;
    private List<SalesItemsRequest> itemList;
}
