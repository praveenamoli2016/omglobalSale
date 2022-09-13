package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.HoldInvoice;
import com.omglobal.omglobal.model.entities.HoldItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldInvoiceRepo extends JpaRepository<HoldInvoice,Long> {

    @Query(value = "SELECT * FROM hold_invoice WHERE (id Like concat('%',:searchValue,'%') or invoiceId LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<HoldInvoice> searchHoldInvoice(String searchValue, Pageable pageable);

}
