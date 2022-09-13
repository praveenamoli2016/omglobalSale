package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.PurchasePayments;
import com.omglobal.omglobal.model.entities.Supplier;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
public class SupplierPaymentRequest {


    private Long id;
    private Long supplierId;
    private Long purchasePaymentsId;
    private Double amount;
    private PaymentType paymentType;
    private String date;
    private String note;
    private Status status=Status.ACTIVE;
    private String createdBy;
    private Boolean isDeleted;

}
