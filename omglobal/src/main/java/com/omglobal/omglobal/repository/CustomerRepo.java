package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT * FROM customer WHERE (customer_name Like concat('%',:searchValue,'%') or email LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<Customer> searchCustomer(String searchValue, Pageable pageable);
}
