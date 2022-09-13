//package com.omglobal.omglobal.repository;
//
//import com.omglobal.omglobal.model.entities.SalesInvoice;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface SalesInviceRepo extends JpaRepository<SalesInvoice,Long> {
//
////    @Query(value = "SELECT * FROM invoice_sale WHERE (id Like concat('%',:searchValue,'%') or hsn LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
////    Page<SalesInvoice> searchSalesInvice(String searchValue, Pageable pageable);
////
////    Optional<SalesInvoice> findById(Long salesId, Long salesId1);
//}
