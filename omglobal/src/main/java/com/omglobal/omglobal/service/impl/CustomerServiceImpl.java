package com.omglobal.omglobal.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omglobal.omglobal.model.entities.*;
import com.omglobal.omglobal.model.request.*;
import com.omglobal.omglobal.model.request.dto.CustomerRequestDTO;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.repository.*;
import com.omglobal.omglobal.service.CustomerService;
import com.omglobal.omglobal.service.UtilityService;
import com.omglobal.omglobal.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.naming.Context;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerPaymentRepo customerPaymentRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private SalesPaymentsRepo salesPaymentsRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private SalesPaymentsReturnRepo salesPaymentsReturnRepo;

    @Autowired
    private SalesReturnRepo salesReturnRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private HoldRepo holdRepo;

//        @Autowired
//        private SalesInviceRepo salesInviceRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private SalesItemsRepo salesItemsRepo;

    @Autowired
    private SalesItemsReturnRepo salesItemsReturnRepo;

    @Autowired
    private HoldItemsRepo holdItemsRepo;

    @Autowired
    private HoldInvoiceRepo holdInvoiceRepo;

    @Autowired
    private AdvanceRepo advanceRepo;

    @Autowired
    private QuotationItemsRepo quotationItemsRepo;

    @Autowired
    private UtilityService utilityService;

    @Override
    public Response addCustomer(CustomerRequest customerRequest) {
        Response response = null;
        try {
            List<CustomerRequestDTO> customerRequestList = customerRequest.getCustomerRequestDTOS();
            List<Customer> customerList = new ArrayList<>();
            /*for(CustomerRequestDTO customerRequestDTO : customerRequestList){

            }*/
            customerRequestList.forEach(customerRequestDTO -> {
                Optional<Customer> customerOptional = Optional.empty();
                Customer customer = null;
                //checking id is present or not
                if (customerRequestDTO.getId() != null)
                    //finding customer using id from DB
                    customerOptional = customerRepo.findById(customerRequestDTO.getId());

                //checking customer is present or not
                if (customerOptional.isPresent()) { //updating existing customer
                    customer = customerOptional.get();
                    customer.setUpdatedAt(LocalDateTime.now().toString());
                } else { //creating new customer
                    customer = new Customer();
                    customer.setCreatedAt(LocalDateTime.now().toString());
                }

                Optional<Store> optionalStore = storeRepo.findById(customerRequestDTO.getStoreId());
                if (optionalStore.isPresent()) {
                    Store store = optionalStore.get();
                    customer.setStore(store);
                }

                //setting all common fields
                customer.setName(customerRequestDTO.getName());
                customer.setAddress(customerRequestDTO.getAddress());
                customer.setCity(customerRequestDTO.getCity());
                customer.setIsDeleted(customerRequestDTO.getIsDeleted() != null ? customerRequestDTO.getIsDeleted() : false);
                customer.setStatus(customerRequestDTO.getStatus() != null ? customerRequestDTO.getStatus() : Status.ACTIVE);
                customer.setCountry(customerRequestDTO.getCountry());
                customer.setCreditLimit(customerRequestDTO.getCreditLimit());
                customer.setEmail(customerRequestDTO.getEmail());
                customer.setMobileNumber(customerRequestDTO.getMobileNumber());
                customer.setPhoneNumber(customerRequestDTO.getPhoneNumber());
                customer.setGstNumber(customerRequestDTO.getTaxNumber());
                customer.setPreviousDue(customerRequestDTO.getPreviousDue());
                customer.setLocationLink(customerRequestDTO.getLocationLink());
                customer.setOpeningBalance(customerRequestDTO.getOpeningBalance());
                customer.setPostCode(customerRequestDTO.getPostCode());
                customer.setShippingAddress(customerRequestDTO.getShippingAddress());
                customer.setShippingCountry(customerRequestDTO.getShippingCountry());
                customer.setShippingCity(customerRequestDTO.getShippingCity());
                customer.setShippingLocationLink(customerRequestDTO.getShippingLocationLink());
                customer.setShippingPostCode(customerRequestDTO.getShippingPostCode());
                customer.setShippingState(customerRequestDTO.getShippingState());
                customer.setState(customerRequestDTO.getState());
                customer.setTaxNumber(customerRequestDTO.getTaxNumber());
                customer.setPriceLevel(customerRequestDTO.getPriceLevel());

                //setting role
                Optional<Role> role = roleRepo.findByRoleNameAndStoreId("CUSTOMER", customerRequestDTO.getStoreId());
                if (role.isPresent())
                    customer.setRole(role.get());
                else {
                    RoleRequest roleRequest = new RoleRequest();
                    roleRequest.setRoleName("CUSTOMER");
                    roleRequest.setStatus(Status.ACTIVE);
                    roleRequest.setDescription("Created Automatic");
                    roleRequest.setStoreId(customerRequestDTO.getStoreId());
                    Response res = utilityService.createRole(roleRequest);
                    //getting role data from response
                    ObjectMapper mapper = new ObjectMapper();
                    Role savedRole = mapper.convertValue(res.getResponse(), Role.class);
                    customer.setRole(savedRole);
                }
                // saving customer in list
                customerList.add(customer);
            });

            //saving list of customer in database
            List<Customer> savedList = customerRepo.saveAll(customerList);
            response = new Response("Success", "200", "Customer saved successfully", savedList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getCustomer(Long customerId) {
        Response response = null;
        try {
            Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                response = new Response("Success", "200", "Get Customer successfully", customer);
            } else {
                response = new Response("Failure", "404", "Customer not found ", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;

    }

    @Override
    public Response getAllCustomer(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
            Page<Customer> customerData = customerRepo.searchCustomer(searchValue, pageable);
            List<Customer> customerList = customerData.getContent();
            response = new Response("Success", "200", "CustomerList fetched successfully", customerList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response addAdvance(AdvanceRequest advanceRequest) {
        Response response = null;
        try {

            // fetching customer details
            Optional<Advance> optionalAdvance = Optional.empty();

//            Optional<CustomerPayment> optionalCustomerPayment = Optional.empty();
//            Customer customer = null;
//            Optional<Customer> optionalCustomer=customerRepo.findById(customerPaymentRequest.getCustomerId());
            Advance advance = null;
            if (advanceRequest.getId() != null) {
                //finding customer using id from DB
                optionalAdvance = advanceRepo.findById(advanceRequest.getId());

                //checking customer is present or not
                if (optionalAdvance.isPresent()) {
                    //updating existing customer
                    advance = optionalAdvance.get();
                    advance.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    //creating new customer
                    advance = new Advance();
                    advance.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                //creating new customer
                advance = new Advance();
                advance.setCreatedAt(LocalDateTime.now().toString());
            }
            //setting customer payment data
//                CustomerPayment customerPayment=new CustomerPayment();
            Optional<Customer> customerOptional = customerRepo.findById(advanceRequest.getCustomerId());
            if (customerOptional.isPresent()) {
                advance.setCustomer(customerOptional.get());
            } else
                throw new Exception("customer not found");
            Optional<Store> optionalStore = storeRepo.findById(advanceRequest.getStoreId());
            if (optionalStore.isPresent()) {
                advance.setStore(optionalStore.get());
            } else
                throw new Exception("store not found");
            //setting all fields
            advance.setAmount(advanceRequest.getAmount());
            advance.setDate(advanceRequest.getDate());
            advance.setNote(advanceRequest.getNote());
            advance.setPaymentType(advanceRequest.getPaymentType());
            advance.setStatus(advanceRequest.getStatus());
            advance.setPaymentCode(advanceRequest.getPaymentCode());
            advance.setIsDeleted(advanceRequest.getIsDeleted() != null ? advanceRequest.getIsDeleted() : false);
            advance.setCreatedBy(advanceRequest.getCreatedBy());


            //saving data in database
            Advance savedAdvance = advanceRepo.save(advance);
            response = new Response("Success", "200", "CustomerPayment saved successfully", savedAdvance);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getAllCustomerAdvancePayment(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
//            int pageSize=5;
//            int pageNumber=1;
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//            Sort sort=null;
//            if (sortDir.equalsIgnoreCase("asc")){
//                sort=Sort.by(sortBy).ascending();
//            }
//            else {
//                sort=Sort.by(sortBy).ascending();
//            }
            //Sort sort = Sort.by(sortBy);
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<Advance> advancesPage = this.advanceRepo.findAll(p);
            List<Advance> advanceList = advancesPage.getContent();
            // List<CustomerPayment> customerPaymentList=customerPaymentRepo.findAll();
            response = new Response("Success", "200", "customer advance paymentlist fetched successfully", advanceList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response addQuotation(QuotationRequest quotationRequest) {
        Response response = null;
        try {
            Optional<Quotation> optionalQuotation = Optional.empty();
            Quotation quotation = null;
            if (quotationRequest.getId() != null) {
                //finding customer using id from DB
                optionalQuotation = quotationRepo.findById(quotation.getId());
            }
            //checking customer is present or not
            if (optionalQuotation.isPresent()) {
                //updating existing customer
                quotation = optionalQuotation.get();
                quotation.setUpdatedAt(LocalDateTime.now().toString());
            } else {
                //creating new customer
                quotation = new Quotation();
                quotation.setCreatedAt(LocalDateTime.now().toString());
            }

            Optional<Store> optionalStore = storeRepo.findById(quotationRequest.getStoreId());
            if (optionalStore.isPresent()) {
                quotation.setStore(optionalStore.get());
            } else {
                throw new Exception("store not found");
            }
            //  fetching customer details
            Optional<Customer> optionalCustomer = customerRepo.findById(quotationRequest.getCustomerId());
            if (optionalCustomer.isPresent()) {
                quotation.setCustomer(optionalCustomer.get());
            } else {

                throw new Exception("Customer not found");
            }
            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(quotationRequest.getWarehouseId());
            if (optionalWarehouse.isPresent()) {
                quotation.setWarehouse(optionalWarehouse.get());
            } else {
                throw new Exception("Warehouse not found");
            }
            //setting all fields

            quotation.setQuotationCode(quotationRequest.getQuotationCode());
            quotation.setQuotationDate(quotationRequest.getQuotationDate());
            quotation.setNote(quotationRequest.getNote());
            quotation.setCreatedBy(quotationRequest.getCreatedBy());
            quotation.setExpireDate(quotationRequest.getExpireDate());
            quotation.setDiscountOnAll(quotationRequest.getDiscountOnAll());
            quotation.setRoundOff(quotationRequest.getRoundOff());
            quotation.setQuotationStatus(quotationRequest.getQuotationStatus());
            quotation.setOtherChargesAmount(quotationRequest.getOtherChargesAmount());
            quotation.setOtherChargesInput(quotationRequest.getOtherChargesInput());
            quotation.setOtherChargesTax(quotationRequest.getOtherChargesTax());
            quotation.setPriviousDue(quotationRequest.getPriviousDue());
            quotation.setSubTotal(quotationRequest.getSubTotal());
            quotation.setReferenceNo(quotationRequest.getReferenceNo());
            quotation.setGrandTotal(quotationRequest.getGrandTotal());
            quotation.setStatus(quotationRequest.getStatus());
            quotation.setPos(quotationRequest.getPos());
            quotation.setReturnBit(quotationRequest.getReturnBit());
            quotation.setDiscountType(quotationRequest.getDiscountType());
            quotation.setDiscountOnAllInput(quotationRequest.getDiscountOnAllInput());
            quotation.setPaymentStatus(quotationRequest.getPaymentStatus());
            quotation.setPaymentAmount(quotationRequest.getPaymentAmount());
            quotation.setTotalDue(quotationRequest.getTotalDue());

            Quotation savedQuotation = quotationRepo.save(quotation);

            response = new Response("Success", "200", "Quotation saved successfully", savedQuotation);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllQuotationList(Long warehouseId, String fromDate, String toDate, Long customerId) {
        Response response = null;
        try {
            String query = "Select q from quotation q where ";

            if (warehouseId != null && warehouseId > 0) {
                String warehouseQuery = "warehouse_id = " + warehouseId;
                query = query + warehouseQuery;
            }

            if (customerId != null && customerId > 0) {
                String customerQuery = "customer_id = " + customerId;
                if (query.length() > 32)
                    query = query + " and " + customerQuery;
                else
                    query = query + customerQuery;
            }
            if (fromDate != null) {
                String fromDateQuery = "created_at >= '" + fromDate + "'";
                if (query.length() > 32)
                    query = query + " and " + fromDateQuery + "";
                else
                    query = query + fromDateQuery;
            }
            if (toDate != null) {

                String toDateQuery = "created_at <= '" + toDate + "'";
                if (query.length() > 32)
                    query = query + " and " + toDateQuery;
                else
                    query = query + toDateQuery;
            }
            if (query.length() <= 32) {
                query = "select q from quotation q";
            }
            System.out.println(query);
            List<Quotation> quotationList = quotationRepo.findCustomNativeQuery(query);
            response = new Response("Success", "200", "Get QuotationList successfully", quotationList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response addSale(SaleRequest saleRequest) {
        Response response = null;
        try {
            Optional<Sale> saleOptional = Optional.empty();
            Sale sale = null;
            if (saleRequest.getId() != null) {
                saleOptional = saleRepo.findById(saleRequest.getId());
                if (saleOptional.isPresent()) {
                    sale = saleOptional.get();
                    sale.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    sale = new Sale();
                    sale.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                //creating new customer
                sale = new Sale();
                sale.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Customer> optionalCustomer = customerRepo.findById(saleRequest.getCustomerId());
            if (optionalCustomer.isPresent()) {
                sale.setCustomer(optionalCustomer.get());
            } else {
                throw new Exception("Customer not found");
            }
            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(saleRequest.getWarehouseId());
            if (optionalWarehouse.isPresent()) {
                sale.setWarehouse(optionalWarehouse.get());
            } else {
                throw new Exception("Warehouse not found");
            }
            Optional<Store> optionalStore = storeRepo.findById(saleRequest.getStoreId());
            if (optionalStore.isPresent()) {
                sale.setStore(optionalStore.get());
            } else {
                throw new Exception("store not found");
            }

            sale.setQuotation(saleRequest.getQuotationId());
            sale.setSalesCode(saleRequest.getSalesCode());
            sale.setSalesDate(saleRequest.getSalesDate());
            sale.setDueDate(saleRequest.getDueDate());
            sale.setSalesStatus(saleRequest.getSalesStatus());
            sale.setOtherChargesInput(saleRequest.getOtherChargesInput());
            sale.setOtherChargesTax(saleRequest.getOtherChargesTax());
            sale.setDiscountOnAllInput(saleRequest.getDiscountOnAllInput());
            sale.setDiscountType(saleRequest.getDiscountType());
            sale.setNote(saleRequest.getNote());
            sale.setPreviousDue(saleRequest.getPreviousDue());
            sale.setPaymentStatus(saleRequest.getPaymentStatus());
            sale.setRoundOff(saleRequest.getRoundOff());
            sale.setOtherChargesAmount(saleRequest.getOtherChargesAmount());
            sale.setTotalDue(saleRequest.getTotalDue());
            sale.setDiscountOnAll(saleRequest.getDiscountOnAll());
            sale.setQuantity(saleRequest.getQuantity());
            sale.setSubTotal(saleRequest.getSubTotal());
            sale.setGrandTotal(saleRequest.getGrandTotal());
            sale.setStatus(saleRequest.getStatus());
            sale.setCreatedBy(saleRequest.getCreatedBy());
            sale.setPaidAmount(saleRequest.getPaidAmount());
            sale.setPos(saleRequest.getPos());

            //saving sale details
            Sale savedSale = saleRepo.save(sale);
            //add payment details
            addCustomerAndSalesPayment(saleRequest.getSalesPaymentsRequest(), optionalCustomer.get(), optionalStore.get(), savedSale);
            //saving saleItems
            List<SalesItems> salesItemsList = saveSaleItemDetails(saleRequest.getItemList(), optionalStore.get(), savedSale);
            response = new Response("Success", "200", "Saved sale successfully", null);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getAllSalesList(Long warehouseId, String fromDate, String toDate, Long customerId) {
        Response response = null;
        try {
            List<Sale> saleList = new ArrayList<>();
            if (warehouseId != null) {
                if (customerId != null) {
                    if (fromDate != null) {
                        if (toDate != null) {
                            saleList = saleRepo.getByWarehouseIdCustomerIdFromAndToDate(warehouseId, customerId, fromDate, toDate);
                        } else {
                            saleList = saleRepo.getByWarehouseIdCustomerIdAndFromDate(warehouseId, customerId, fromDate);
                        }
                    } else if (toDate != null) {
//                        saleList =  saleRepo.findByWarehouseIdAndCustomerId(warehouseId, customerId);
                        saleList = saleRepo.getByWarehouseIdCustomerIdAndToDate(warehouseId, customerId, toDate);
                    } else {
                        saleList = saleRepo.findByWarehouseIdAndCustomerId(warehouseId, customerId);
                    }
                } else if (fromDate != null) {
                    if (toDate != null) {
                        saleList = saleRepo.getByWarehouseIdFromAndToDate(warehouseId, fromDate, toDate);
                    } else {
                        saleList = saleRepo.getByWarehouseIdAndFromDate(warehouseId, fromDate);
                    }
                } else if (toDate != null) {
//                        saleList =  saleRepo.findByWarehouseIdAndCustomerId(warehouseId, customerId);
                    saleList = saleRepo.getByWarehouseIdCustomerIdAndToDate(warehouseId, customerId, toDate);
                } else {
                    saleList = saleRepo.findByWarehouseId(warehouseId);
                }
            } else if (customerId != null) {
                if (fromDate != null) {
                    if (toDate != null) {
                        saleList = saleRepo.getByCustomerAndFromDateAndToDate(customerId, fromDate, toDate);

                    } else {
                        saleList = saleRepo.getByCustomerAndFromDate(customerId, fromDate);
                    }
                } else if (toDate != null) {
                    saleList = saleRepo.getByCustomerAndToDate(customerId, toDate);

                } else {
                    saleList = saleRepo.findByCustomerId(customerId); //query not required
                }
            } else if (fromDate != null) {
                if (toDate != null) {
                    saleList = saleRepo.getByFromAndToDate(fromDate, toDate);
                } else {
                    saleList = saleRepo.getByFromDate(fromDate);
                }
            } else if (toDate != null) {
                saleList = saleRepo.getByToDate(toDate);
            } else {
                saleList = saleRepo.findAll();
            }

            response = new Response("Success", "200", "SaleList fetched successfully", saleList);
//            response=new Response("Success","200","SaleList fromDate fetched successfully",list);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }

        return response;
    }

        @Override
        public Response addSalesPayments(SalesPaymentsRequest salesPaymentsRequest) {
            Response response = null;
            try {
                Optional<SalesPayments> optionalSalesPayments = Optional.empty();
                SalesPayments salesPayments = null;
                if (salesPaymentsRequest.getId() != null) {

                    optionalSalesPayments = salesPaymentsRepo.findById(salesPaymentsRequest.getId());

                    if (optionalSalesPayments.isPresent()) {
                        //updating existing customer
                        salesPayments = optionalSalesPayments.get();
                        salesPayments.setUpdatedAt(LocalDateTime.now().toString());
                    } else {
                        //creating new customer
                        salesPayments = new SalesPayments();
                        salesPayments.setCreatedAt(LocalDateTime.now().toString());
                    }
                } else {
                    //creating new customer
                    salesPayments = new SalesPayments();
                    salesPayments.setCreatedAt(LocalDateTime.now().toString());
                }
                //  fetching customer details
                Optional<Customer> optionalCustomer = customerRepo.findById(salesPaymentsRequest.getCustomerId());
                if (optionalCustomer.isPresent()) {
                    salesPayments.setCustomer(optionalCustomer.get());
                } else {

                    throw new Exception("Customer not found");
                }
                Optional<Sale> optionalSale = saleRepo.findById(salesPaymentsRequest.getSaleId());
                if (optionalSale.isPresent()) {
                    salesPayments.setSale(optionalSale.get());
                } else {
                    throw new Exception("SaleId not found");
                }
                Optional<Store> optionalStore = storeRepo.findById(salesPaymentsRequest.getStoreId());
                if (optionalStore.isPresent()) {
                    salesPayments.setStore(optionalStore.get());
                } else {
                    throw new Exception("store not found");
                }

                salesPayments.setAccount(salesPaymentsRequest.getAccountId());
                salesPayments.setAdjustAdvancePayment(salesPaymentsRequest.getAdjustAdvancePayment());
                salesPayments.setPaymentAmount(salesPaymentsRequest.getPaymentAmount());
                salesPayments.setPaymentType(salesPaymentsRequest.getPaymentType());
                salesPayments.setPaymentCode(salesPaymentsRequest.getPaymentCode());
                salesPayments.setPaymentDate(salesPaymentsRequest.getPaymentDate());
                salesPayments.setPaymentNote(salesPaymentsRequest.getPaymentNote());
                salesPayments.setAdvancePayment(salesPayments.getAdvancePayment());
                salesPayments.setChequeNumber(salesPaymentsRequest.getChequeNumber());
                salesPayments.setChequePeriod(salesPaymentsRequest.getChequePeriod());
                salesPayments.setChequeStatus(salesPaymentsRequest.getChequeStatus());
                salesPayments.setCreatedBy(salesPaymentsRequest.getCreatedBy());
                salesPayments.setStatus(salesPaymentsRequest.getStatus());
                salesPayments.setPaymentStatus(salesPaymentsRequest.getPaymentStatus());

                SalesPayments savedSalesPayment = salesPaymentsRepo.save(salesPayments);
                response = new Response("Success", "200", "Saved salePayment successfully", savedSalesPayment);
            } catch (Exception exception) {
                response = new Response("Failure", "500", exception.getMessage(), null);

            }
            return response;
        }

    @Override
    public Response getSalesPatments(Long salesPaymentsId) {
        Response response = null;
        try {
            Optional<SalesPayments> optionalSalesPayments = salesPaymentsRepo.findById(salesPaymentsId);
            if (optionalSalesPayments.isPresent()) {
                SalesPayments salesPayments = optionalSalesPayments.get();
                response = new Response("Success", "200", "Get salesPayment successfully", salesPayments);
            } else {
                response = new Response("Failure", "404", "SalesPaymentId not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllSalesPatments(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<SalesPayments> paymentPage = this.salesPaymentsRepo.findAll(p);
            List<SalesPayments> salesPaymentsList = paymentPage.getContent();
            // List<SalesPayments> salesPaymentsList=salesPaymentsRepo.findAll();
            response = new Response("Success", "200", "List of salesPayments get successfully", salesPaymentsList);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response addSalesReturn(SalesReturnRequest salesReturnRequest) {
        Response response = null;
        try {
            Optional<SalesReturn> optionalSalesReturn = Optional.empty();
            SalesReturn salesReturn = null;
            if (salesReturnRequest.getId() != null) {
                optionalSalesReturn = salesReturnRepo.findById(salesReturnRequest.getId());

                if (optionalSalesReturn.isPresent()) {
                    //updating existing customer
                    salesReturn = optionalSalesReturn.get();
                    salesReturn.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    //creating new customer
                    salesReturn = new SalesReturn();
                    salesReturn.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                //creating new customer
                salesReturn = new SalesReturn();
                salesReturn.setCreatedAt(LocalDateTime.now().toString());
            }

            Optional<Sale> optionalSale = saleRepo.findById(salesReturnRequest.getSaleId());
            if (optionalSale.isPresent()) {
                salesReturn.setSale(optionalSale.get());
            } else {
                throw new Exception("Sale not found");
            }

            salesReturn.setWarehouse(salesReturnRequest.getWarehouseId());
            salesReturn.setCustomer(salesReturnRequest.getCustomerId());
            salesReturn.setItem(salesReturnRequest.getItemId());
            salesReturn.setReferenceNo(salesReturnRequest.getReferenceNo());
            salesReturn.setReturnCode(salesReturnRequest.getReturnCode());
            salesReturn.setReturnDate(salesReturnRequest.getReturnDate());
            salesReturn.setRoundOff(salesReturnRequest.getRoundOff());
            salesReturn.setReturnStatus(salesReturnRequest.getReturnStatus());
            salesReturn.setReturnNote(salesReturnRequest.getReturnNote());
            salesReturn.setCreatedBy(salesReturnRequest.getCreatedBy());
            salesReturn.setStatus(salesReturnRequest.getStatus());
            salesReturn.setPayment(salesReturnRequest.getPayment());
            salesReturn.setPaymentStatus(salesReturnRequest.getPaymentStatus());
            salesReturn.setSubTotal(salesReturnRequest.getSubTotal());
            salesReturn.setQuantity(salesReturnRequest.getQuantity());
            salesReturn.setDiscountOnAll(salesReturnRequest.getDiscountOnAll());
            salesReturn.setOtherChargesAmount(salesReturnRequest.getOtherChargesAmount());
            salesReturn.setOtherChargesInput(salesReturnRequest.getOtherChargesInput());
            salesReturn.setGrandTotal(salesReturnRequest.getGrandTotal());
            salesReturn.setOtherChargesTax(salesReturnRequest.getOtherChargesTax());
            salesReturn.setDiscountOnAllInput(salesReturnRequest.getDiscountOnAllInput());
            salesReturn.setDiscountType(salesReturnRequest.getDiscountType());


            SalesReturn savedSalesReturn = salesReturnRepo.save(salesReturn);
            response = new Response("Success", "200", "Saved salesReturnItem successfully", savedSalesReturn);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getSalesReturnItem(Long salesReturnId) {
        Response response = null;
        try {
            Optional<SalesReturn> optionalSalesReturn = salesReturnRepo.findById(salesReturnId);
            if (optionalSalesReturn.isPresent()) {
                SalesReturn salesReturn = optionalSalesReturn.get();
                response = new Response("Success", "200", "Get salesReturnItem successfully", salesReturn);
            } else {
                response = new Response("Failure", "404", "SalesReturnId not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getAllSalesReturnList(Long warehouseId) {
        Response response = null;
        try {
            List<SalesReturn> salesReturnList = new ArrayList<>();
            if (warehouseId != null) {
                salesReturnList = salesReturnRepo.getByWarehouseId(warehouseId);
            } else {
                salesReturnList = salesReturnRepo.findAll();
            }
            response = new Response("Success", "200", "get allSalesReturnList successfully", salesReturnList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response addHoldItems(HoldItemsRequest holdItemsRequest) {
        Response response = null;
        try {
            Optional<HoldItems> optionalHoldItems = Optional.empty();
            HoldItems holdItems = null;
            if (holdItemsRequest.getId() != null) {
                optionalHoldItems = holdItemsRepo.findById(holdItemsRequest.getId());
                if (optionalHoldItems.isPresent()) {
                    //updating existing customer
                    holdItems = optionalHoldItems.get();
                    holdItems.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    //creating new customer
                    holdItems = new HoldItems();
                    holdItems.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                holdItems = new HoldItems();
                holdItems.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Store> optionalStore = storeRepo.findById(holdItemsRequest.getStoreId());
            if (optionalStore.isPresent()) {
                holdItems.setStore(optionalStore.get());
            } else {
                throw new Exception("store not found");
            }
            Optional<Item> optionalItem = itemRepo.findById(holdItemsRequest.getItemId());
            if (optionalItem.isPresent()) {
                holdItems.setItem(optionalItem.get());
            } else {
                throw new Exception("Item not found");
            }

//            List<Item> itemList = new ArrayList<>();
//            holdRequest.getItemSet().forEach(itemId-> {
//                Optional<Item> itemOptional = itemRepo.findById(itemId);
//                if (itemOptional.isPresent())
//                    itemList.add(itemOptional.get());
//            });

//            {

//            }
//              List<Item> items=new ArrayList<>();
//
//              holdRequest.getItemSet().forEach(i->{
//                  Optional<Item> optionalItem=itemRepo.findById(i);
//                  if (optionalItem.isPresent()){
//                  Item item=optionalItem.get();
//                  item.setHoldSet(item.getHoldSet());
//              items.add(item);}
//              });
//               hold.setItemSet(items);
//            List<Item> itemList = itemRepo.getAllById(holdRequest.getItemSet());
            //  hold.setItemSet(itemList);

//}

            holdItems.setDescription(holdItemsRequest.getDescription());
            holdItems.setQuantity(holdItemsRequest.getQuantity());
            holdItems.setStatus(holdItemsRequest.getStatus());
            holdItems.setUnitPrice(holdItemsRequest.getUnitPrice());
            holdItems.setUnitTotalCost(holdItemsRequest.getUnitTotalCost());
            holdItems.setCreatedBy(holdItemsRequest.getCreatedBy());
            holdItems.setDiscount(holdItemsRequest.getDiscount());
            holdItems.setDiscountInput(holdItemsRequest.getDiscountInput());
            holdItems.setDiscountType(holdItemsRequest.getDiscountType());
            holdItems.setTax(holdItemsRequest.getTaxId());
            holdItems.setTaxAmount(holdItemsRequest.getTaxAmount());
            holdItems.setTaxType(holdItemsRequest.getTaxType());
            holdItems.setTotalCost(holdItemsRequest.getTotalCost());

            HoldItems savedHoldItems = holdItemsRepo.save(holdItems);
            response = new Response("Success", "200", "Saved holditems successfully", savedHoldItems);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getHoldItem(Long holdItemsId) {
        Response response = null;
        try {
            Optional<HoldItems> optionalHoldItems = holdItemsRepo.findById(holdItemsId);
            if (optionalHoldItems.isPresent()) {
                HoldItems holdItems = optionalHoldItems.get();
                response = new Response("Success", "200", "Get holdItem successfully", holdItems);
            } else {
                response = new Response("Failure", "404", "Hold item not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getAllHoldItems(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<HoldItems> holdItemsPage = this.holdItemsRepo.findAll(p);
            List<HoldItems> holdItems = holdItemsPage.getContent();
            response = new Response("Success", "200", "List of all hold item get successfully", holdItems);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }


    @Override
    public Response addSalesPaymentsReturn(SalesPaymentsReturnRequest salesPaymentsReturnRequest) {

        Response response = null;
        try {
            SalesPaymentsReturn salesPaymentsReturn = null;
            if (salesPaymentsReturnRequest.getId() != null) {
                Optional<SalesPaymentsReturn> optionalSalesPaymentsReturn = salesPaymentsReturnRepo.findById(salesPaymentsReturnRequest.getId());
                if (optionalSalesPaymentsReturn.isPresent()) {
                    salesPaymentsReturn = optionalSalesPaymentsReturn.get();
                    salesPaymentsReturn.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    salesPaymentsReturn = new SalesPaymentsReturn();
                    salesPaymentsReturn.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                salesPaymentsReturn = new SalesPaymentsReturn();
                salesPaymentsReturn.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Customer> optionalCustomer = customerRepo.findById(salesPaymentsReturnRequest.getCustomerId());
            if (optionalCustomer.isPresent()) {
                salesPaymentsReturn.setCustomer(optionalCustomer.get());
            } else {
                throw new Exception("Customer not found");
            }
            Optional<Store> optionalStore = storeRepo.findById(salesPaymentsReturnRequest.getStoreId());
            if (optionalStore.isPresent()) {
                salesPaymentsReturn.setStore(optionalStore.get());
            } else {
                throw new Exception("Store not found");
            }
            Optional<SalesReturn> optionalSalesReturn = salesReturnRepo.findById(salesPaymentsReturnRequest.getSalesReturnId());
            if (optionalSalesReturn.isPresent()) {
                salesPaymentsReturn.setSalesReturn(optionalSalesReturn.get());
            } else {
                throw new Exception("SalesReturn not found");
            }


            salesPaymentsReturn.setAccount(salesPaymentsReturnRequest.getAccountId());
            salesPaymentsReturn.setCreatedBy(salesPaymentsReturnRequest.getCreatedBy());
            salesPaymentsReturn.setPaymentCode(salesPaymentsReturnRequest.getPaymentCode());
            salesPaymentsReturn.setPaymentDate(salesPaymentsReturnRequest.getPaymentDate());
            salesPaymentsReturn.setPaymentNote(salesPaymentsReturnRequest.getPaymentNote());
            salesPaymentsReturn.setPaymentType(salesPaymentsReturnRequest.getPaymentType());
            salesPaymentsReturn.setPaymentStatus(salesPaymentsReturnRequest.getPaymentStatus());
            salesPaymentsReturn.setChangeReturn(salesPaymentsReturnRequest.getChangeReturn());
            salesPaymentsReturn.setShortCode(salesPaymentsReturnRequest.getShortCode());
            salesPaymentsReturn.setStatus(salesPaymentsReturnRequest.getStatus());
            salesPaymentsReturn.setPaymentAmount(salesPaymentsReturnRequest.getPaymentAmount());


            SalesPaymentsReturn savedSalesPaymentsReturn = salesPaymentsReturnRepo.save(salesPaymentsReturn);
            response = new Response("Success", "200", "SalesPaymentsReturn saved successfully", savedSalesPaymentsReturn);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getSalesPaymentReturnById(Long salesPaymentsReturnId) {
        Response response = null;
        try {
            Optional<SalesPaymentsReturn> salesPaymentsReturnOptional = salesPaymentsReturnRepo.findById(salesPaymentsReturnId);
            if (salesPaymentsReturnOptional.isPresent()) {
                SalesPaymentsReturn salesPaymentsReturn = salesPaymentsReturnOptional.get();
                response = new Response("Success", "200", "fetched salesPaymentsReturn successfully", salesPaymentsReturn);
            } else {
                response = new Response("Failure", "404", "salesPaymentsReturn not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllSalesPaymentReturnList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<SalesPaymentsReturn> salesPaymentsReturnsPage = this.salesPaymentsReturnRepo.findAll(p);
            List<SalesPaymentsReturn> salesPaymentsReturnList = salesPaymentsReturnsPage.getContent();
            response = new Response("Success", "200", "salesPaymentsReturnlist fetched successfully", salesPaymentsReturnList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    private List<SalesItems> saveSaleItemDetails(List<SalesItemsRequest> salesItemsRequestList, Store store, Sale sale) {
        List<SalesItems> salesItemsList = new ArrayList<>();
        salesItemsRequestList.forEach(salesItemsRequest -> {
            SalesItems salesItems = null;
            if (salesItemsRequest.getId() != null) {
                Optional<SalesItems> optionalSalesItems = salesItemsRepo.findById(salesItemsRequest.getId());
                if (optionalSalesItems.isPresent()) {
                    salesItems = optionalSalesItems.get();
                    salesItems.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    salesItems = new SalesItems();
                    salesItems.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                salesItems = new SalesItems();
                salesItems.setCreatedAt(LocalDateTime.now().toString());
            }
            //  setting sale details
            salesItems.setSale(sale);
            salesItems.setStore(store);
            salesItems.setItem(salesItemsRequest.getItemId());
            salesItems.setCreatedBy(salesItemsRequest.getCreatedBy());
            salesItems.setSalesItemStatus(salesItemsRequest.getSalesItemStatus());
            salesItems.setDescription(salesItemsRequest.getDescription());
            salesItems.setQuantity(salesItemsRequest.getQuantity());
            salesItems.setUnitPrice(salesItemsRequest.getUnitPrice());
            salesItems.setDiscountInput(salesItemsRequest.getDiscountInput());
            salesItems.setDiscount(salesItemsRequest.getDiscount());
            salesItems.setDiscountType(salesItemsRequest.getDiscountType());
            salesItems.setStatus(salesItemsRequest.getStatus());
            salesItems.setTax(salesItemsRequest.getTax());
            salesItems.setTaxAmount(salesItemsRequest.getTaxAmount());
            salesItems.setTaxType(salesItemsRequest.getTaxType());
            salesItems.setUnitTotalCost(salesItemsRequest.getUnitTotalCost());
            salesItems.setTotalCost(salesItemsRequest.getTotalCost());
            salesItems.setSellerPoints(salesItemsRequest.getSellerPoints());
            salesItemsList.add(salesItems);
        });

        List<SalesItems> savedSalesItems = salesItemsRepo.saveAll(salesItemsList);
        return savedSalesItems;
    }

    @Override
    public Response getSalesItemsById(Long salesItemsId) {
        Response response = null;
        try {
            Optional<SalesItems> optionalSalesItems = salesItemsRepo.findById(salesItemsId);
            if (optionalSalesItems.isPresent()) {
                SalesItems salesItems = optionalSalesItems.get();
                response = new Response("Success", "200", "fetched salesItems successfully", salesItems);
            } else {
                response = new Response("Failure", "404", "salesItems not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllSalesItemsList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<SalesItems> salesItemsPage = this.salesItemsRepo.findAll(p);
            List<SalesItems> salesItemsList = salesItemsPage.getContent();
            response = new Response("Success", "200", "salesItemslist fetched successfully", salesItemsList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response addSalesItemsReturn(SalesItemsReturnRequest salesItemsReturnRequest) {
        Response response = null;
        try {
            SalesItemsReturn salesItemsReturn = null;
            if (salesItemsReturnRequest.getId() != null) {
                Optional<SalesItemsReturn> optionalSalesItemsReturn = salesItemsReturnRepo.findById(salesItemsReturnRequest.getId());
                if (optionalSalesItemsReturn.isPresent()) {
                    salesItemsReturn = optionalSalesItemsReturn.get();
                    salesItemsReturn.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    salesItemsReturn = new SalesItemsReturn();
                    salesItemsReturn.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                salesItemsReturn = new SalesItemsReturn();
                salesItemsReturn.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<SalesReturn> optionalSalesReturn = salesReturnRepo.findById(salesItemsReturnRequest.getId());
            if (optionalSalesReturn.isPresent()) {
                salesItemsReturn.setSalesReturn(optionalSalesReturn.get());
            } else {
                throw new Exception("SalesReturn not found");
            }
            Optional<Store> optionalStore = storeRepo.findById(salesItemsReturnRequest.getStoreId());
            if (optionalStore.isPresent()) {
                salesItemsReturn.setStore(optionalStore.get());
            } else {
                throw new Exception("Store not found");
            }


            salesItemsReturn.setItem(salesItemsReturnRequest.getItemId());
            salesItemsReturn.setCreatedBy(salesItemsReturnRequest.getCreatedBy());
            salesItemsReturn.setSalesItemReturnStatus(salesItemsReturnRequest.getSalesItemReturnStatus());
            salesItemsReturn.setDiscription(salesItemsReturnRequest.getDiscription());
            salesItemsReturn.setReturnQuantity(salesItemsReturnRequest.getReturnQuantity());
            salesItemsReturn.setUnitPrice(salesItemsReturnRequest.getUnitPrice());
            salesItemsReturn.setDiscountInput(salesItemsReturnRequest.getDiscountInput());
            salesItemsReturn.setDiscount(salesItemsReturnRequest.getDiscount());
            salesItemsReturn.setDiscountType(salesItemsReturnRequest.getDiscountType());
            salesItemsReturn.setStatus(salesItemsReturnRequest.getStatus());
            salesItemsReturn.setTax(salesItemsReturnRequest.getTax());
            salesItemsReturn.setTaxAmount(salesItemsReturnRequest.getTaxAmount());
            salesItemsReturn.setTaxType(salesItemsReturnRequest.getTaxType());
            salesItemsReturn.setUnitTotalCost(salesItemsReturnRequest.getUnitTotalCost());
            salesItemsReturn.setTotalCost(salesItemsReturnRequest.getTotalCost());


            SalesItemsReturn savedSalesItemsReturn = salesItemsReturnRepo.save(salesItemsReturn);
            response = new Response("Success", "200", "SalesItemsReturn saved successfully", savedSalesItemsReturn);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getSalesItemsReturnById(Long salesItemsReturnId) {

        Response response = null;
        try {
            Optional<SalesItemsReturn> optionalSalesItemsReturn = salesItemsReturnRepo.findById(salesItemsReturnId);
            if (optionalSalesItemsReturn.isPresent()) {
                SalesItemsReturn salesItemsReturn = optionalSalesItemsReturn.get();
                response = new Response("Success", "200", "fetched sales return items successfully", salesItemsReturn);
            } else {
                response = new Response("Failure", "404", "sales return items not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllSalesItemsReturnList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<SalesItemsReturn> salesItemsReturnsPage = this.salesItemsReturnRepo.findAll(p);
            List<SalesItemsReturn> salesItemsReturnList = salesItemsReturnsPage.getContent();
            response = new Response("Success", "200", "salesItemsReturnlist fetched successfully", salesItemsReturnList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    public void addCustomerAndSalesPayment(SalesPaymentsRequest salesPaymentsRequest, Customer customer, Store store, Sale sale) {
        //saving sales payment
        SalesPayments salesPayments = null;
        if (salesPaymentsRequest.getId()!= null) {
            Optional<SalesPayments> optionalSalesPayments = salesPaymentsRepo.findById(salesPaymentsRequest.getId());
            if (optionalSalesPayments.isPresent()) {
                salesPayments = optionalSalesPayments.get();
                salesPayments.setUpdatedAt(LocalDateTime.now().toString());
            } else {
                salesPayments = new SalesPayments();
                salesPayments.setCreatedAt(LocalDateTime.now().toString());
            }
        } else {
            salesPayments = new SalesPayments();
            salesPayments.setCreatedAt(LocalDateTime.now().toString());
        }

        salesPayments.setSale(sale);
        salesPayments.setAdvancePayment(salesPaymentsRequest.getAdvancePayment());
        salesPayments.setPaymentStatus(salesPaymentsRequest.getPaymentStatus());
        salesPayments.setPaymentNote(salesPaymentsRequest.getPaymentNote());
        salesPayments.setAdjustAdvancePayment(salesPaymentsRequest.getAdjustAdvancePayment());
        salesPayments.setAccount(salesPaymentsRequest.getAccountId());
        salesPayments.setChequePeriod(salesPaymentsRequest.getChequePeriod());
        salesPayments.setChequeNumber(salesPaymentsRequest.getChequeNumber());
        salesPayments.setChangeReturn(salesPaymentsRequest.getChangeReturn());
        salesPayments.setCustomer(customer);
        salesPayments.setShortCode(salesPaymentsRequest.getShortCode());
        salesPayments.setStore(store);
        salesPayments.setChequeStatus(salesPaymentsRequest.getChequeStatus());
        salesPayments.setPaymentDate(salesPaymentsRequest.getPaymentDate());
        salesPayments.setStatus(salesPaymentsRequest.getStatus());
        salesPayments.setPaymentCode(salesPaymentsRequest.getPaymentCode());
        salesPayments.setPaymentType(salesPaymentsRequest.getPaymentType());
        salesPayments.setCreatedBy(salesPaymentsRequest.getCreatedBy());
        SalesPayments savedSalesPayment = salesPaymentsRepo.save(salesPayments);

        //saving customer payment
        CustomerPayment customerPayment = null;
        if (salesPaymentsRequest.getId() != null) {
            Optional<CustomerPayment> customerPaymentOptional = customerPaymentRepo.findBySalesPayments(savedSalesPayment);
            if (customerPaymentOptional.isPresent()) {
                customerPayment = customerPaymentOptional.get();
                customerPayment.setUpdatedAt(LocalDateTime.now().toString());
            } else {
                customerPayment = new CustomerPayment();
                customerPayment.setCreatedAt(LocalDateTime.now().toString());
            }
        } else {
            customerPayment = new CustomerPayment();
            customerPayment.setCreatedAt(LocalDateTime.now().toString());
        }
        customerPayment.setCustomer(customer);
        customerPayment.setAmount(salesPaymentsRequest.getPaymentAmount());
        customerPayment.setPaymentType(salesPaymentsRequest.getPaymentType());
        customerPayment.setNote(salesPaymentsRequest.getPaymentNote());
        customerPayment.setDate(salesPaymentsRequest.getPaymentDate());
        customerPayment.setStatus(salesPaymentsRequest.getStatus());
        customerPayment.setSalesPayments(savedSalesPayment);
        customerPaymentRepo.save(customerPayment);
    }

    @Override
    public Response getCustomerPaymentById(Long customerPaymentId) {
        Response response = null;
        try {
            Optional<CustomerPayment> optionalCustomerPayment = customerPaymentRepo.findById(customerPaymentId);
            if (optionalCustomerPayment.isPresent()) {
                CustomerPayment customerPayment = optionalCustomerPayment.get();
                response = new Response("Success", "200", "fetched customer payment successfully", customerPayment);
            } else {
                response = new Response("Failure", "404", "customer payment not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllCustomerPaymentList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<CustomerPayment> customerPaymentsPage = this.customerPaymentRepo.findAll(p);
            List<CustomerPayment> customerPaymentList = customerPaymentsPage.getContent();
            response = new Response("Success", "200", "customer payment list fetched successfully", customerPaymentList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response addQuotationItems(QuotationItemsRequest quotationItemsRequest) {
        Response response = null;
        try {
            QuotationItems quotationItems = null;
            if (quotationItemsRequest.getId() != null) {
                Optional<QuotationItems> optionalQuotationItems = quotationItemsRepo.findById(quotationItemsRequest.getId());
                if (optionalQuotationItems.isPresent()) {
                    quotationItems = optionalQuotationItems.get();
                    quotationItems.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    quotationItems = new QuotationItems();
                    quotationItems.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                quotationItems = new QuotationItems();
                quotationItems.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Store> optionalStore = storeRepo.findById(quotationItemsRequest.getStoreId());
            if (optionalStore.isPresent()) {
                quotationItems.setStore(optionalStore.get());
            } else {
                throw new Exception("store not found");
            }
            Optional<Quotation> optionalQuotation = quotationRepo.findById(quotationItemsRequest.getQuotationId());
            if (optionalQuotation.isPresent()) {
                quotationItems.setQuotation(optionalQuotation.get());
            } else {
                throw new Exception("quotation not found");
            }


            quotationItems.setItem(quotationItemsRequest.getItemId());
            quotationItems.setDescription(quotationItemsRequest.getDescription());
            quotationItems.setQuantity(quotationItemsRequest.getQuantity());
            quotationItems.setUnitPrice(quotationItemsRequest.getUnitPrice());
            quotationItems.setUnitTotalCost(quotationItemsRequest.getUnitTotalCost());
            quotationItems.setStatus(quotationItemsRequest.getStatus());
            quotationItems.setDiscount(quotationItemsRequest.getDiscount());
            quotationItems.setDiscountInput(quotationItemsRequest.getDiscountInput());
            quotationItems.setDiscountType(quotationItemsRequest.getDiscountType());
            quotationItems.setSellerPoints(quotationItemsRequest.getSellerPoints());
            quotationItems.setTax(quotationItemsRequest.getTax());
            quotationItems.setTaxAmount(quotationItemsRequest.getTaxAmount());
            quotationItems.setTotalCost(quotationItemsRequest.getTotalCost());
            quotationItems.setTaxType(quotationItemsRequest.getTaxType());
            quotationItems.setCreatedBy(quotationItemsRequest.getCreatedBy());

            QuotationItems savedQuotationItems = quotationItemsRepo.save(quotationItems);
            response = new Response("Success", "200", "quotation items saved successfully", savedQuotationItems);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getQuotationItemsById(Long quotationItemsId) {
        Response response = null;
        try {
            Optional<QuotationItems> optionalQuotationItems = quotationItemsRepo.findById(quotationItemsId);
            if (optionalQuotationItems.isPresent()) {
                QuotationItems quotationItems = optionalQuotationItems.get();
                response = new Response("Success", "200", "fetched quotation items successfully", quotationItems);
            } else {
                response = new Response("Failure", "404", " quotation items not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getQuotationItemsList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<QuotationItems> quotationItemsPage = this.quotationItemsRepo.findAll(p);
            List<QuotationItems> quotationItemsList = quotationItemsPage.getContent();
            response = new Response("Success", "200", "quotation items list fetched successfully", quotationItemsList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response addHoldInvoice(HoldInvoiceRequest holdInvoiceRequest) {
        Response response = null;
        try {
            HoldInvoice holdInvoice = null;
            if (holdInvoiceRequest.getId() != null) {
                Optional<HoldInvoice> optionalHoldInvoice = holdInvoiceRepo.findById(holdInvoiceRequest.getId());
                if (optionalHoldInvoice.isPresent()) {
                    holdInvoice = optionalHoldInvoice.get();
                    holdInvoice.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    holdInvoice = new HoldInvoice();
                    holdInvoice.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                holdInvoice = new HoldInvoice();
                holdInvoice.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Store> optionalStore = storeRepo.findById(holdInvoiceRequest.getStoreId());
            if (optionalStore.isPresent()) {
                holdInvoice.setStore(optionalStore.get());
            } else {
                throw new Exception("store not found");
            }


            holdInvoice.setInvoiceId(holdInvoiceRequest.getInvoiceId());
            holdInvoice.setDate(holdInvoiceRequest.getDate());
            holdInvoice.setQuantity(holdInvoiceRequest.getQuantity());
            holdInvoice.setReferenceId(holdInvoiceRequest.getReferenceId());
            holdInvoice.setItemId(holdInvoiceRequest.getItemId());
            holdInvoice.setStatus(holdInvoiceRequest.getStatus());
            holdInvoice.setPos(holdInvoiceRequest.getPos());
            holdInvoice.setTax(holdInvoiceRequest.getTax());
            holdInvoice.setPrice(holdInvoiceRequest.getPrice());
            holdInvoice.setCreatedBy(holdInvoiceRequest.getCreatedBy());

            HoldInvoice savedHoldInvoice = holdInvoiceRepo.save(holdInvoice);
            response = new Response("Success", "200", "hold invoice saved successfully", savedHoldInvoice);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getHoldInvoiceById(Long holdInvoiceId) {
        Response response = null;
        try {
            Optional<HoldInvoice> optionalHoldInvoice = holdInvoiceRepo.findById(holdInvoiceId);
            if (optionalHoldInvoice.isPresent()) {
                HoldInvoice holdInvoice = optionalHoldInvoice.get();
                response = new Response("Success", "200", "fetched hold invoice successfully", holdInvoice);
            } else {
                response = new Response("Failure", "404", " hold invoice not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getAllHoldInvoiceList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response = null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<HoldInvoice> holdInvoicePage = this.holdInvoiceRepo.findAll(p);
            List<HoldInvoice> holdInvoiceList = holdInvoicePage.getContent();
            response = new Response("Success", "200", "hold invoice list fetched successfully", holdInvoiceList);


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getSaleById(Long saleId) {
        Response response = null;
        try {
            Optional<Sale> optionalSale = saleRepo.findById(saleId);
            if (optionalSale.isPresent()) {
                Sale sale = optionalSale.get();
                response = new Response("Success", "200", "fetched sale details successfully", sale);
            } else {
                response = new Response("Failure", "404", " sale not found", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }
}

