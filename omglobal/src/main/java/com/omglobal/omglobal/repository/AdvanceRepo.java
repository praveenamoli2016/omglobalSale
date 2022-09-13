package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Advance;
import com.omglobal.omglobal.model.entities.HoldItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceRepo extends JpaRepository<Advance,Long> {

    @Query(value = "SELECT * FROM hold_items WHERE (id Like concat('%',:searchValue,'%') or amount LIKE CONCAT('%',:searchValue,'%')) and is_deleted = false",nativeQuery = true)
    Page<Advance> searchAdvance(String searchValue, Pageable pageable);

}
