package com.omglobal.omglobal.repository;

//import com.omglobal.omglobal.model.entities.SalesInvoice;
import com.omglobal.omglobal.model.entities.SalesPaymentsReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPaymentsReturnRepo extends JpaRepository<SalesPaymentsReturn,Long> {

    @Query(value = "SELECT * FROM sales_payments_return WHERE (id Like concat('%',:searchValue,'%') or salesReturnId LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<SalesPaymentsReturn> searchSalesPaymentsReturn(String searchValue, Pageable pageable);

}
