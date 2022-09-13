package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Sale;
import com.omglobal.omglobal.model.entities.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesReturnRepo extends JpaRepository<SalesReturn,Long> {

    @Query(value = "select * from sales_return where warehouse_id =:warehouseId",nativeQuery = true)
    List<SalesReturn> getByWarehouseId(java.lang.Long warehouseId);



}
