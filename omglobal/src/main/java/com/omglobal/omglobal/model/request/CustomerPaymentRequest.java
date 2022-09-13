package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.utility.enums.PaymentType;
import lombok.Data;

@Data
public class CustomerPaymentRequest {
    private Long paymentId;
    private Double amount;
    private String account;
    private Boolean advance;
    private PaymentType paymentType;
    private String date;
    private String paymentNote;
    private String status;
    private Boolean isDeleted;
}
