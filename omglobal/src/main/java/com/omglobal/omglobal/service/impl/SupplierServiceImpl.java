package com.omglobal.omglobal.service.impl;

import com.omglobal.omglobal.model.entities.*;
import com.omglobal.omglobal.model.request.SupplierPaymentRequest;
import com.omglobal.omglobal.model.request.SupplierRequest;
import com.omglobal.omglobal.model.request.dto.SupplierRequestDTO;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.repository.PurchasePaymentsRepo;
import com.omglobal.omglobal.repository.StoreRepo;
import com.omglobal.omglobal.repository.SupplierPaymentRepo;
import com.omglobal.omglobal.repository.SupplierRepo;
import com.omglobal.omglobal.service.SupplierService;
import com.omglobal.omglobal.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private SupplierPaymentRepo supplierPaymentRepo;

    @Autowired
    private PurchasePaymentsRepo purchasePaymentsRepo;

    @Override
    public Response addSupplier(SupplierRequest supplierRequest) {
        Response response = null;
        try {
            List<SupplierRequestDTO> supplierRequestList = supplierRequest.getSupplierRequestDTOS();
            List<Supplier> supplierList = new ArrayList<>();
            supplierRequestList.forEach(supplierRequestDTO -> {
                Optional<Supplier> supplierOptional = Optional.empty();
                Supplier supplier = null;
                //checking id is present or not
                if (supplierRequestDTO.getId() != null)
                    //finding customer using id from DB
                    supplierOptional = supplierRepo.findById(supplierRequestDTO.getId());

                //checking supplier is present or not
                if (supplierOptional.isPresent()) { //updating existing customer
                    supplier = supplierOptional.get();
                    supplier.setUpdatedAt(LocalDateTime.now().toString());
                } else { //creating new customer
                    supplier = new Supplier();
                    supplier.setCreatedAt(LocalDateTime.now().toString());
                }
                Optional<Store>optionalStore=storeRepo.findById(supplierRequestDTO.getStoreId());
                if (optionalStore.isPresent()){
                    Store store=optionalStore.get();
                    supplier.setStore(store);
                }

                //setting all common fields
                supplier.setName(supplierRequestDTO.getName());
                supplier.setAddress(supplierRequestDTO.getAddress());
                supplier.setCity(supplierRequestDTO.getCity());
                supplier.setIsDeleted(supplierRequestDTO.getIsDeleted() != null ? supplierRequestDTO.getIsDeleted() : false);
                supplier.setStatus(supplierRequestDTO.getStatus() != null ? supplierRequestDTO.getStatus() : Status.ACTIVE);
                supplier.setCountry(supplierRequestDTO.getCountry());
                supplier.setEmail(supplierRequestDTO.getEmail());
                supplier.setMobileNumber(supplierRequestDTO.getMobileNumber());
                supplier.setGstNumber(supplierRequestDTO.getGstNumber());
                supplier.setOpeningBalance(supplierRequestDTO.getOpeningBalance());
                supplier.setPostCode(supplierRequestDTO.getPostCode());
                supplier.setState(supplierRequestDTO.getState());
                supplier.setTaxNumber(supplierRequestDTO.getTaxNumber());
                supplier.setCreatedBy(supplierRequestDTO.getCreatedBy());

                //saving customer in list
                supplierList.add(supplier);
            });
            //saving list of supplier in database
            List<Supplier> savedList = supplierRepo.saveAll(supplierList);
            response = new Response("Success", "200", "Supplier saved successfully", savedList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getSupplier(Long supplierId) {
        Response response=null;
        try {
            Optional<Supplier> supplierOptional=supplierRepo.findById(supplierId);
            if (supplierOptional.isPresent()) {
                Supplier supplier = supplierOptional.get();
                response = new Response("Success", "200", "Get Supplier successfully", supplier);
            }
            else {
                response = new Response("Failure", "404", "Supplier not found ", null);

            }
        }catch (Exception exception){
            response=new Response("Failure","500",exception.getMessage(),null);

        }
        return response;
    }

    @Override
    public Response getAllSupplier(String searchValue,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Response response=null;
        try {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Supplier> supplierPage = this.supplierRepo.findAll(p);
        List<Supplier> supplierList = supplierPage.getContent();
            response=new Response("Success","200","SupplierList fetched Successfully",supplierList);
        }catch (Exception exception){
            response=new Response("Failure","500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response addSupplierPayment(SupplierPaymentRequest supplierPaymentRequest) {
        Response response = null;
        try {
            SupplierPayment supplierPayment = null;
            if (supplierPaymentRequest.getId() != null) {
                Optional<SupplierPayment> optionalSupplierPayment = supplierPaymentRepo.findById(supplierPaymentRequest.getId());
                if (optionalSupplierPayment.isPresent()) {
                    supplierPayment = optionalSupplierPayment.get();
                    supplierPayment.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    supplierPayment = new SupplierPayment();
                    supplierPayment.setCreatedAt(LocalDateTime.now().toString());
                }
            } else {
                supplierPayment = new SupplierPayment();
                supplierPayment.setCreatedAt(LocalDateTime.now().toString());
            }
            //  fetching customer details
            Optional<Supplier> optionalSupplier = supplierRepo.findById(supplierPaymentRequest.getSupplierId());
            if (optionalSupplier.isPresent()) {
                supplierPayment.setSupplier(optionalSupplier.get());
            } else {
                throw new Exception("supplier not found");
            }
            Optional<PurchasePayments> optionalPurchasePayments = purchasePaymentsRepo.findById(supplierPaymentRequest.getPurchasePaymentsId());
            if (optionalPurchasePayments.isPresent()) {
                supplierPayment.setPurchasePayments(optionalPurchasePayments.get());
            } else {
                throw new Exception("purchase payments not found");
            }


            supplierPayment.setAmount(supplierPaymentRequest.getAmount());
            supplierPayment.setPaymentType(supplierPaymentRequest.getPaymentType());
            supplierPayment.setNote(supplierPaymentRequest.getNote());
            supplierPayment.setDate(supplierPaymentRequest.getDate());
            supplierPayment.setStatus(supplierPaymentRequest.getStatus());
            supplierPayment.setIsDeleted(supplierPaymentRequest.getIsDeleted()!=null?supplierPaymentRequest.getIsDeleted():false);
            supplierPayment.setCreatedBy(supplierPaymentRequest.getCreatedBy());

            SupplierPayment savedSupplierPayment =supplierPaymentRepo.save(supplierPayment);
            response=new Response("Success","200","supplier payment saved successfully", savedSupplierPayment);
        }catch (Exception exception){
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response getSupplierPaymentById(Long supplierPaymentId) {
        Response response=null;
        try {
            Optional<SupplierPayment> optionalSupplierPayment=supplierPaymentRepo.findById(supplierPaymentId);
            if (optionalSupplierPayment.isPresent()) {
                SupplierPayment supplierPayment = optionalSupplierPayment.get();
                response = new Response("Success", "200", "fetch Supplier payment successfully", supplierPayment);
            }
            else {
                response = new Response("Failure", "404", "supplier payment not found ", null);

            }
        }catch (Exception exception){
            response=new Response("Failure","500",exception.getMessage(),null);

        }
        return response;
    }

    @Override
    public Response getAllSupplierPaymentList(String searchValue, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Response response=null;
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
            Pageable p = PageRequest.of(pageNumber, pageSize, sort);
            Page<SupplierPayment> supplierPaymentsPage = this.supplierPaymentRepo.findAll(p);
            List<SupplierPayment> supplierPaymentList = supplierPaymentsPage.getContent();
            response=new Response("Success","200","SupplierPaymentList fetched Successfully",supplierPaymentList);
        }catch (Exception exception){
            response=new Response("Failure","500", exception.getMessage(), null);
        }
        return response;
    }
}
