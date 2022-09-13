package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.model.entities.Warehouse;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class QuotationRequest {


    private Long id;
    private Long storeId;
    private Long customerId;
    private Long warehouseId;
    private String quotationCode;
    private String quotationDate;
    private String expireDate;
    private String referenceNo;
    private String quotationStatus;
    private Double otherChargesInput;
    private Double otherChargesAmount;
    private Double otherChargesTax;
    private Double discountOnAllInput;
    private Double discountOnAll;
    private DiscountType discountType;
    private Double subTotal;
    private Double roundOff;
    private Double grandTotal;
    private Double paymentAmount;
    private PaymentStatus paymentStatus;
    private Double priviousDue;
    private Double totalDue;
    private String note;
    private Integer pos;
    private Integer returnBit;
    private Status status=Status.ACTIVE;
    private String createdBy;

}
