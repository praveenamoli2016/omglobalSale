package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.SalesReturn;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.PaymentStatus;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
public class SalesPaymentsReturnRequest {

    private Long id;
    private Long storeId;
    private Long customerId;
    private Long salesReturnId ;
    private Long accountId;
    private String paymentCode;
    private String paymentDate;
    private Double paymentAmount;
    private PaymentType paymentType;
    private String paymentNote;
    private PaymentStatus paymentStatus;
    private Double changeReturn ;
    private String shortCode ;
    private Status status = Status.ACTIVE;
    private String createdBy;

}
