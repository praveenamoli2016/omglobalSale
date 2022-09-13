package com.omglobal.omglobal.service;

import com.omglobal.omglobal.model.request.SupplierPaymentRequest;
import com.omglobal.omglobal.model.request.SupplierRequest;
import com.omglobal.omglobal.model.response.Response;

public interface SupplierService {
    Response addSupplier(SupplierRequest supplierRequest);

    Response getSupplier(Long supplierId);

    Response getAllSupplier(String searchValue,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    Response addSupplierPayment(SupplierPaymentRequest supplierPaymentRequest);

    Response getSupplierPaymentById(Long supplierPaymentId);

    Response getAllSupplierPaymentList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
