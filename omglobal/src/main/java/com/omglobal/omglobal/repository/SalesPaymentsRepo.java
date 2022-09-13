package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.CustomerPayment;
import com.omglobal.omglobal.model.entities.SalesPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPaymentsRepo extends JpaRepository<SalesPayments,Long> {


    @Query(value = "SELECT * FROM sales_payments WHERE (id Like concat('%',:searchValue,'%') or adjust_advance LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<SalesPayments> searchSalesPayments(String searchValue, Pageable pageable);

}
