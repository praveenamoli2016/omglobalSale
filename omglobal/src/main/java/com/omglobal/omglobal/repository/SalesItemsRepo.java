package com.omglobal.omglobal.repository;

//import com.omglobal.omglobal.model.entities.SalesInvoice;
import com.omglobal.omglobal.model.entities.SalesItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemsRepo extends JpaRepository<SalesItems,Long> {

    @Query(value = "SELECT * FROM sales_items WHERE (id Like concat('%',:searchValue,'%') or itemId LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<SalesItems> searchSalesItems(String searchValue, Pageable pageable);

}
