package com.omglobal.omglobal.service;

import com.omglobal.omglobal.model.request.*;
import com.omglobal.omglobal.model.response.Response;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    Response addCustomer(CustomerRequest customerRequest);

    Response getCustomer(Long customerId);

    Response getAllCustomer(String searchValue, Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

    Response addAdvance(AdvanceRequest advanceRequest);

    Response getAllCustomerAdvancePayment(String searchValue,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    Response addQuotation(QuotationRequest quotationRequest);

    Response getAllQuotationList(Long warehouseId,String fromDate,String toDate,Long customerId);

    Response addSale(SaleRequest saleRequest);

    Response getAllSalesList(Long warehouseId, String fromDate, String toDate, Long customerId);

     Response addSalesPayments(SalesPaymentsRequest salesPaymentsRequest);

    Response getSalesPatments(Long salesPaymentsId);

    Response getAllSalesPatments(String searchValue,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    Response addSalesReturn(SalesReturnRequest salesReturnRequest);

    Response getSalesReturnItem(Long salesReturnId);

    Response getAllSalesReturnList(Long warehouseId);

    Response addHoldItems(HoldItemsRequest holdItemsRequest);

    Response getHoldItem(Long holdItemsId);

    Response getAllHoldItems(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

//    Response addSalesInvoice(SalesInvoiceRequest salesInvoiceRequest);

   // Response getSalesInviceById(Long salesInviceId);

   // Response getAllSalesInvice(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response addSalesPaymentsReturn(SalesPaymentsReturnRequest salesPaymentsReturnRequest);

    Response getSalesPaymentReturnById(Long salesPaymentsReturnId);

    Response getAllSalesPaymentReturnList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response getSalesItemsById(Long salesItemsId);

    Response getAllSalesItemsList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response addSalesItemsReturn(SalesItemsReturnRequest salesItemsReturnRequest);

    Response getSalesItemsReturnById(Long salesItemsReturnId);

    Response getAllSalesItemsReturnList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response getCustomerPaymentById(Long customerPaymentId);

    Response getAllCustomerPaymentList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response addQuotationItems(QuotationItemsRequest quotationItemsRequest);

    Response getQuotationItemsById(Long quotationItemsId);

    Response getQuotationItemsList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response addHoldInvoice(HoldInvoiceRequest holdInvoiceRequest);

    Response getHoldInvoiceById(Long holdInvoiceId);

    Response getAllHoldInvoiceList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    Response getSaleById(Long saleId);

    // ResponseEntity downloadSalesInvoice(Long salesId);
}
