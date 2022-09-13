package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    @Query(value = "Select * from warehouse where id in (:warehouseIds)", nativeQuery = true)
    List<Warehouse> findByIds(Set<Long> warehouseIds);
}
