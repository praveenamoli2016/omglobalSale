package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "hold_invoice")
public class HoldInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store ;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_date")
    private String date;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "hsn")
    private Long itemId;

    @Column(name = "item_quantity")
    private Integer quantity;

    @Column(name = "item_price")
    private Double price;

    @Column(name = "tax")
    private String tax;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;





}
