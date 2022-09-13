package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.utility.enums.PaymentType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
public class AdvanceRequest {


    private Long id;
    private Long storeId;
    private Long customerId;
    private String paymentCode;
    private String date;
    private Double amount;
    private PaymentType paymentType;
    private String note;
    private Boolean isDeleted;
    private Status status=Status.ACTIVE;
    private String createdBy;

}
