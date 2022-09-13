package com.omglobal.omglobal.controller;

//import com.omglobal.omglobal.model.entities.DatabaseFile;
import com.omglobal.omglobal.model.request.*;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.service.CustomerService;
//import com.omglobal.omglobal.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("${project.image}")
    private String path;

    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addCustomer")
    public Response addCustomer(@Valid  @RequestBody CustomerRequest customerRequest){
        return this.customerService.addCustomer(customerRequest);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getCustomer")
    public Response getCustomer(@Valid@RequestParam Long customerId){
        return this.customerService.getCustomer(customerId);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @GetMapping("/getAllCustomer")
    public Response getAllCustomer(@Valid@RequestParam(defaultValue = "") String searchValue, @RequestParam(defaultValue = "0") Integer pageNumber ,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "customer_name") String sortBy,
                                   @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllCustomer(searchValue, pageNumber, pageSize, sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addAdvance")
    public Response addAdvance(@Valid@ RequestBody AdvanceRequest advanceRequest){
        return this.customerService.addAdvance(advanceRequest);
    }


   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllCustomerAdvancePayment")
    public Response getAllCustomerAdvancePayment(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                                 @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                 @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                                 @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                                 @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir){
        return this.customerService.getAllCustomerAdvancePayment(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @PostMapping("/addQuotation")
    public Response addQuotation(@Valid @RequestBody QuotationRequest quotationRequest){
        return this.customerService.addQuotation(quotationRequest);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @GetMapping("/getAllQuotationList")
    public Response getAllQuotationList(Long warehouseId,String fromDate,String toDate,Long customerId) {
        return this.customerService.getAllQuotationList(warehouseId, fromDate,toDate,customerId);
    }


   // @PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @PostMapping("/addQuotationItems")
    public Response addQuotationItems(@Valid@RequestBody QuotationItemsRequest quotationItemsRequest){
        return this.customerService.addQuotationItems(quotationItemsRequest);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @GetMapping("/getQuotationItemsById")
    public Response getQuotationItemsById(@Valid Long quotationItemsId) {
        return this.customerService.getQuotationItemsById(quotationItemsId);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @GetMapping("/getQuotationItemsList")
    public Response getQuotationItemsList(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                        @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                        @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                        @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getQuotationItemsList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @PostMapping("/addSale")
    public Response addSale(@Valid @RequestBody SaleRequest saleRequest){
        return this.customerService.addSale(saleRequest);
    }

    @GetMapping("/getSaleById")
    public Response getSaleById(@Valid @RequestParam Long saleId){
        return this.customerService.getSaleById(saleId);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getAllSalesList")
    public Response getAllSalesList(Long warehouseId,String fromDate,String toDate,Long customerId) {
        return this.customerService.getAllSalesList(warehouseId, fromDate,toDate,customerId);
    }


//    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addSalesPayments")
    public Response addSalesPayments(@Valid@RequestBody SalesPaymentsRequest salesPaymentsRequest){
        return this.customerService.addSalesPayments(salesPaymentsRequest);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getSalesPatments")
    public Response getSalesPatments(@Valid Long salesPaymentsId) {
        return this.customerService.getSalesPatments(salesPaymentsId);
    }


   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSalesPatments")
    public Response getAllSalesPatments(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                        @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                        @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                        @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllSalesPatments(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addSalesReturn")
    public Response addSalesReturn(@Valid @RequestBody SalesReturnRequest salesReturnRequest){
        return this.customerService.addSalesReturn(salesReturnRequest);
    }


   // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getSalesReturnItem")
    public Response getSalesReturnItem(@Valid Long salesReturnId) {
        return this.customerService.getSalesReturnItem(salesReturnId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSalesReturnList")
    public Response getAllSalesReturnList(@Valid Long warehouseId) {
        return this.customerService.getAllSalesReturnList(warehouseId);
    }


   // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addSalesPaymentsReturn")
    public Response addSalesPaymentsReturn(@Valid @RequestBody SalesPaymentsReturnRequest salesPaymentsReturnRequest){
        return this.customerService.addSalesPaymentsReturn(salesPaymentsReturnRequest);
    }


   // @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getSalesPaymentReturnById")
    public Response getSalesPaymentReturnById(@Valid Long salesPaymentsReturnId) {
        return this.customerService.getSalesPaymentReturnById(salesPaymentsReturnId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSalesPaymentReturnList")
    public Response getAllSalesPaymentReturnList(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                                 @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                 @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                                 @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                                 @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllSalesPaymentReturnList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getSalesItemsById")
    public Response getSalesItemsById(@Valid Long salesItemsId) {
        return this.customerService.getSalesItemsById(salesItemsId);
    }


    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllSalesItemsList")
    public Response getAllSalesItemsList(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                                 @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                 @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                                 @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                                 @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllSalesItemsList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @PostMapping("/addSalesItemsReturn")
    public Response addSalesItemsReturn(@Valid @RequestBody SalesItemsReturnRequest salesItemsReturnRequest){
        return this.customerService.addSalesItemsReturn(salesItemsReturnRequest);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getSalesItemsReturnById")
    public Response getSalesItemsReturnById(@Valid Long salesItemsReturnId) {
        return this.customerService.getSalesItemsReturnById(salesItemsReturnId);
    }


   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSalesItemsReturnList")
    public Response getAllSalesItemsReturnList(@Valid @RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                         @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                         @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                         @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                         @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllSalesItemsReturnList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/addHoldItems")
    public Response addHoldItems(@Valid @RequestBody HoldItemsRequest holdItemsRequest){
        return this.customerService.addHoldItems(holdItemsRequest);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getHoldItem")
    public Response getHoldItem(@Valid @RequestParam Long holdItemsId) {
        return this.customerService.getHoldItem(holdItemsId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllHoldItems")
    public Response getAllHoldItems(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                        @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                        @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                        @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllHoldItems(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }



//    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
//    @GetMapping("/getSalesInviceById")
//    public Response getSalesInviceById(@Valid@RequestParam Long salesInviceId) {
//        return this.customerService.getSalesInviceById(salesInviceId);
//    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/getAllSalesInvice")
//    public Response getAllSalesInvice(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
//                                    @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
//                                    @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
//                                    @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
//                                    @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
//        return this.customerService.getAllSalesInvice(searchValue,pageNumber,pageSize,sortBy,sortDir);
//    }

    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getCustomerPaymentById")
    public Response getCustomerPaymentById(@Valid @RequestParam Long customerPaymentId) {
        return this.customerService.getCustomerPaymentById(customerPaymentId);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/getAllCustomerPaymentList")
    public Response getAllCustomerPaymentList(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                      @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                      @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                      @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                      @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllCustomerPaymentList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }



    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addHoldInvoice")
    public Response addHoldInvoice(@Valid@RequestBody HoldInvoiceRequest holdInvoiceRequest){
        return this.customerService.addHoldInvoice(holdInvoiceRequest);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @GetMapping("/getHoldInvoiceById")
    public Response getHoldInvoiceById(@Valid@RequestParam Long holdInvoiceId) {
        return this.customerService.getHoldInvoiceById(holdInvoiceId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllHoldInvoiceList")
    public Response getAllHoldInvoiceList(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                              @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                              @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                              @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                              @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.customerService.getAllHoldInvoiceList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


}
