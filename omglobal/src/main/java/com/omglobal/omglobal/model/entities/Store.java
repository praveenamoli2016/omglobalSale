package com.omglobal.omglobal.model.entities;

import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "store_code")
    private String storeCode;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "store_website")
    private String storeWebsite;

    @Column(name = "bank_details")
    private String bankDetails;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "address")
    private String address;

    @Column(name = "store_logo")
    private String storeLogo;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "date_format")
    private String dateFormat;

    @Column(name = "time_format")
    private Integer timeFormat;

    @OneToOne
    @JoinColumn (name = "currency_id")
    private Currency currency;

    @Column(name = "currency_symbol_placement")
    private String currencySymbolPlacement;

    @Column(name = "decimals")
    private Integer decimals;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "enable_round_off")
    private Boolean enableRoundOff;

    @Column(name = "sales_discount")
    private Double defaultSalesDiscount;

    @Column(name = "change_return")
    private Boolean showPaidAmount;

    @Column(name = "sales_invoice_format")
    private Integer salesInvoiceFormat;

    @Column(name = "pos_invoice_format")
    private Integer posInvoiceFormat;

    @Column(name = "sales_invoice_footer_text")
    private String salesInvoiceFooterText;

    @Column(name = "mrp_column")
    private Boolean showMRPColumn;

    @Column(name = "category_init")
    private String category;

    @Column(name = "item_init")
    private String item;

    @Column(name = "supplier_init")
    private String supplier;

    @Column(name = "purchase_init")
    private String purchase;

    @Column(name = "purchase_return_init")
    private String purchaseReturn;

    @Column(name = "customer_init")
    private String customer;

    @Column(name = "sales_init")
    private String sales;

    @Column(name = "sales_return_init")
    private String salesReturn;

    @Column(name = "expense_init")
    private String expense;

    @Column(name = "accounts_init")
    private String accounts;

    @Column(name = "quotation_init")
    private String quotation;

    @Column(name = "money_transfer_init")
    private String moneyTransfer;

    @Column(name = "sales_payment_init")
    private String salesPayment;

    @Column(name = "sales_return_payment_init")
    private String salesReturnPayment;

    @Column(name = "purchase_payment_init")
    private String purchasePayment;

    @Column(name = "purchase_return_payment_init")
    private String purchaseReturnPayment;

    @Column(name = "expense_payment_init")
    private String expensePayment;

    @Column(name = "customers_advance_init")
    private String customersAdvance;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<User> users;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Role> roles;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Purchase> purchases ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<PurchasePayments> purchasePayments ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<SalesItems> salesItems ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Sale> saleSet ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<SalesPayments> salesPayments ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<SalesPaymentsReturn> salesPaymentsReturns ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<SalesItemsReturn> salesItemsReturns ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<SalesReturn> salesReturns ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Hold> holds ;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<HoldItems> holdItems;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Customer> customers;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Supplier> suppliers;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<Quotation> quotations;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "store")
//    private Set<QuotationItems> quotationItems;

}
