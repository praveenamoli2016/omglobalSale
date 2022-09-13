package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omglobal.omglobal.utility.enums.PriceLevelType;
import com.omglobal.omglobal.utility.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @Column(name = "previous_due")
    private String previousDue;

    @Column(name = "opening_balance")
    private Double openingBalance;

    @Column(name = "country_name")
    private String country;

    @Column(name = "state_name")
    private String state;

    @Column(name = "city_name")
    private String city;

    @Column(name = "post_code")
    private Long postCode;

    @Lob
    @Column(name = "location_link",length = 65535)
    private String locationLink;

    @Column(name = "address")
    private String address;

    @Column(name = "shippingCountry_name")
    private String shippingCountry;

    @Column(name = "shippingState_name")
    private String shippingState;

    @Column(name = "shippingCity_name")
    private String shippingCity;

    @Column(name = "shippingPost_code")
    private Long shippingPostCode;

    @Column(name = "shippingLocation_link")
    private String shippingLocationLink;

    @Column(name = "shippingAddress_name")
    private String shippingAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "price_level")
    private Double priceLevel;

    @Column(name = "price_levelType")
    private PriceLevelType priceLevelType;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "customer")
    private Set<CustomerPayment> customerPayment;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "customer")
    private Set<Quotation> quotations;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "customer")
//    private Set<Sale> sales;


    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "customer")
    private Set<SalesPayments> salesPayments;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "customer")
    private Set<Hold> holds;

//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @OneToMany(mappedBy = "customer")
//    private Set<SalesInvoice> salesInvoices;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "customer")
    private Set<SalesPaymentsReturn> salesPaymentsReturns;
}
