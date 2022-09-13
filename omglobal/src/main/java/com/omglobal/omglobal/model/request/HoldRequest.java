package com.omglobal.omglobal.model.request;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.Item;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.model.entities.Warehouse;
import com.omglobal.omglobal.utility.enums.DiscountType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class HoldRequest {

    private Long id;
    private Long customerId;
    private Long warehouseId;
    private Long storeId;
    private String referenceId;
    private String referenceNo;
    private String date;
    private String note;
    private String salesStatus;
    private Double otherChargesInput;
    private Double otherChargesAmount;
    private Double otherChargesTax;
    private Double discountOnAllInput;
    private Double discountOnAll;
    private DiscountType discountType;
    private Double subTotal;
    private Double roundOff;
    private Double grandTotal;
    private int pos;
    private Status status = Status.ACTIVE;
    private String createdBy;

}
