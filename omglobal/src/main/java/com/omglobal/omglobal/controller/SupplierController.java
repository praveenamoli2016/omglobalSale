package com.omglobal.omglobal.controller;

import com.omglobal.omglobal.model.request.CustomerRequest;
import com.omglobal.omglobal.model.request.SupplierPaymentRequest;
import com.omglobal.omglobal.model.request.SupplierRequest;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @PostMapping("/addSupplier")
    public Response addSupplier(@Valid @RequestBody SupplierRequest supplierRequest){
        return this.supplierService.addSupplier(supplierRequest);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @GetMapping("/getSupplier")
    public Response getSupplier(@Valid @RequestParam Long supplierId){

        return this.supplierService.getSupplier(supplierId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSupplier")
    public Response getAllSupplier(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                   @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                   @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                   @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                   @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.supplierService.getAllSupplier(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }


    //@PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/addSupplierPayment")
    public Response addSupplierPayment(@Valid @RequestBody SupplierPaymentRequest supplierPaymentRequest){
        return this.supplierService.addSupplierPayment(supplierPaymentRequest);
    }


    //@PreAuthorize("hasAnyRole('SUPPLIER','ADMIN')")
    @GetMapping("/getSupplierPaymentById")
    public Response getSupplierPaymentById(@Valid @RequestParam Long supplierPaymentId){

        return this.supplierService.getSupplierPaymentById(supplierPaymentId);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSupplierPaymentList")
    public Response getAllSupplierPaymentList(@Valid@RequestParam(value = "searchValue",defaultValue = "",required = false)String searchValue,
                                   @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                   @RequestParam(value="pageSize",defaultValue="10",required = false) Integer pageSize,
                                   @RequestParam(value ="sortBy",defaultValue = "",required = false) String sortBy,
                                   @RequestParam(value = "sortDtr",defaultValue = "asc",required = false)String sortDir) {
        return this.supplierService.getAllSupplierPaymentList(searchValue,pageNumber,pageSize,sortBy,sortDir);
    }
}
