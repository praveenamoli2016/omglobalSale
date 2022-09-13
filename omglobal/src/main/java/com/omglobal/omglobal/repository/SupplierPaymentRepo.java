package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Supplier;
import com.omglobal.omglobal.model.entities.SupplierPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierPaymentRepo extends JpaRepository<SupplierPayment,Long> {
    @Query(value = "SELECT * FROM supplier_payment WHERE (id Like concat('%',:searchValue,'%') or amount LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<SupplierPayment> searchSupplierPayment(String searchValue, Pageable pageable);

}
