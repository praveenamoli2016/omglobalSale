package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.ItemGroup;
import com.omglobal.omglobal.utility.enums.TaxType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Set;

@Data
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn (name = "tax_id")
    private Tax tax;



    @NotBlank(message = "Name is mandatory")
    @Column(name = "item_name")
    private String name;

    @NotBlank
    @Column(name = "item_code")
    private String itemCode;

    @NotNull
    @Column(name = "bar_code")
    private String barCode;

    @NotNull
    @Column(name = "hsn_code")
    private String hsnCode;

    @Column(name = "sku")
    private String sku;

    @Column(name = "seller_points")
    private String sellerPoints;

    @Column(name = "item_group")
    private ItemGroup itemGroup;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Long price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "tax_type")
    private TaxType taxType;

    @Column(name = "sub_total")
    private String subTotal;

    @Column(name = "description")
    private String description;

    @Column(name = "item_image")
    private Blob image;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "profit_margin")
    private Double profitMargin;

    @Column(name = "sales_price")
    private Double salesPrice;

    @Column(name = "mrp")
    private Double mrp;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;



//    @ManyToMany(mappedBy = "itemSet")
//    @JsonIgnore
//    private List<Hold> holdSet;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "item")
//    private Set<SalesInvoice> salesInvoices;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "item")
    private Set<HoldItems> holdItems;
}
