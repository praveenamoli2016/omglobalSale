package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.SalesPayments;
import com.omglobal.omglobal.model.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long> {

    @Query(value = "SELECT * FROM supplier WHERE (id Like concat('%',:searchValue,'%') or name LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<Supplier> searchSupplier(String searchValue, Pageable pageable);

}
