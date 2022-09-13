package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.HoldItems;
import com.omglobal.omglobal.model.entities.QuotationItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationItemsRepo extends JpaRepository<QuotationItems,Long> {

    @Query(value = "SELECT * FROM quotation_items WHERE (id Like concat('%',:searchValue,'%') or quotationId LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<QuotationItems> searchQuotationItems(String searchValue, Pageable pageable);

}
