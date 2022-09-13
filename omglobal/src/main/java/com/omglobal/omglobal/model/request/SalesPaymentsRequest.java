package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.Sale;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
public class SalesPaymentsRequest {


    private Long id;
    private Long customerId;
    private Long saleId ;
    private Long storeId ;
    private Long accountId;
    private String paymentCode;
    private String paymentDate;
    private Double paymentAmount;
    private PaymentType paymentType;
    private Double changeReturn ;
    private String paymentNote;
    private PaymentStatus paymentStatus;
    private String shortCode ;
    private Boolean adjustAdvancePayment ;
    private Double advancePayment ;
    private String chequeNumber;
    private Long chequePeriod;
    private String chequeStatus;
    private Status status = Status.ACTIVE;
    private String createdBy;


}

