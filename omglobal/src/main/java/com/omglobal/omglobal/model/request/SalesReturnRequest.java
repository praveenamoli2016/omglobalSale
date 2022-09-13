package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Sale;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class SalesReturnRequest {

    private Long id;
    private Long storeId;
    private Long customerId;
    private Long warehouseId;
    private Long saleId;
    private Long itemId;
    private String returnCode;
    private String returnDate;
    private String referenceNo;
    private String returnStatus;
    private String returnNote;
    private Double payment;
    private PaymentStatus paymentStatus;
    private Integer quantity;
    private Double otherChargesInput;
    private Double otherChargesAmount;
    private Double otherChargesTax;
    private Double discountOnAllInput;
    private Double discountOnAll;
    private DiscountType discountType;
    private Double subTotal;
    private Double roundOff;
    private Double grandTotal;
    private Integer returnBit;
    private Status status = Status.ACTIVE;
    private String createdBy;

}
