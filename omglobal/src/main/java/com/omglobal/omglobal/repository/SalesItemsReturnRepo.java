package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.SalesItems;
import com.omglobal.omglobal.model.entities.SalesItemsReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemsReturnRepo extends JpaRepository<SalesItemsReturn,Long> {

    @Query(value = "SELECT * FROM sales_items_return WHERE (id Like concat('%',:searchValue,'%') or itemId LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<SalesItemsReturn> searchSalesItemsReturn(String searchValue, Pageable pageable);

}
