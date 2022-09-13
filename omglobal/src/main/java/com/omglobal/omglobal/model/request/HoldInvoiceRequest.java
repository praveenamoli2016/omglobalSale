package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
public class HoldInvoiceRequest {


    private Long id;
    private Long storeId;
    private Long invoiceId;
    private String date;
    private String referenceId;
    private Long itemId;
    private Integer quantity;
    private Double price;
    private String tax;
    private Integer pos;
    private Status status=Status.ACTIVE;
    private String createdBy;

}
