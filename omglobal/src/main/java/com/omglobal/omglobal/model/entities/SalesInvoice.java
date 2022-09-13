//package com.omglobal.omglobal.model.entities;
//
//import com.fasterxml.jackson.annotation.JsonIdentityReference;
//import com.omglobal.omglobal.utility.enums.Status;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Data
//@Entity(name = "invoice_sale")
//public class SalesInvoice {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "store_id")
//    private Store store ;
//
////    @Column(name = "sr_no")
////    private Integer serialNumber;
////
////    @Column(name = "description_of_goods")
////    private String descriptionOfGoods;
////
////    @Column(name = "hsn")
////    private String hsnCode;
////
////    @Column(name = "quantity")
////    private Integer quantity;
////
////    @Column(name = "rates")
////    private Double rate;
//
////    @Column(name = "taxable_amount")
////    private Double taxableAmount;
//
////    @Column(name = "tax")
////    private String tax;
//
//    @Column(name = "total_amount")
//    private Double totalAmount;
//
//    @Column(name = "pos")
//    private Integer pos;
//
//    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
//    private Status status=Status.ACTIVE;
//
//    @Column(name = "created_by")
//    private String createdBy;
//
//    @Column(name = "created_at")
//    private String createdAt;
//
//    @Column(name = "updated_at")
//    private String updatedAt;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "customer_id")
//    private Customer customer;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "warehouse_id")
//    private Warehouse warehouse;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "item_id")
//    private Set<Item> item;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "brand_id")
//    private Brand brand;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "category_id")
//    private Category category;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "hold_id")
//    private Hold hold;
//
//    @JsonIdentityReference(alwaysAsId=true)
//    @ManyToOne
//    @JoinColumn (name = "sale_id")
//    private Sale sale;
//}
