//package com.omglobal.omglobal.model.request;
//
//import com.omglobal.omglobal.model.request.dto.ItemDetailsDto;
//import com.omglobal.omglobal.utility.enums.Status;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.util.Set;
//
//@Data
//public class SalesInvoiceRequest {
//
//    private Long id;
//    private Long storeId ;
//    private Long customerId;
//    private Long warehouseId;
//    private Long saleId;
//    private Set<ItemDetailsDto> itemIds;
//    private Long holdId;
//    private Long brandId;
//    private Long categoryId;
//    @NotNull
//    private Integer serialNumber;
//    @NotNull
//    private String descriptionOfGoods;
//    @NotNull
//    private String hsnCode;
//    @NotNull
//    private Integer quantity;
//    @NotNull
//    private Double rate;
//    @NotNull
//    private Double taxableAmount;
//    @NotNull
//    private String tax;
//    @NotNull
//    private Double totalAmount;
//    @NotNull
//    private String createdBy;
//    @NotNull
//    private Status status=Status.ACTIVE;
//    private Integer pos;
//
//
//}
