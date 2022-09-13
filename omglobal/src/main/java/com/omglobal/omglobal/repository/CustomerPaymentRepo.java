package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.entities.CustomerPayment;
import com.omglobal.omglobal.model.entities.SalesPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerPaymentRepo extends JpaRepository<CustomerPayment,Long> {

    @Query(value = "SELECT * FROM customer_payment WHERE (id Like concat('%',:searchValue,'%') or payment_amount LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<CustomerPayment> searchCustomerPayment(String searchValue, Pageable pageable);

    Optional<CustomerPayment> findBySalesPayments(SalesPayments savedSalesPayment);
}
