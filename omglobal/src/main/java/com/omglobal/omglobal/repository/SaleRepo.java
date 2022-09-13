package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepo extends JpaRepository<Sale,Long> {
//    @Query("select s from Sale s where"+"s.salesCode like concat('%',:query,'%')" )
//    List<Sale> searchSalesList(String query);

//    @Query(value = "select s from sale s where"+"s.salesCode like concat('%',:query,'%')",nativeQuery = true )
//    List<Sale> searchSalesList(String query);
    @Query(value = "select * from sale where created_at>=:fromDate and created_at<=:toDate ",nativeQuery = true)
    List<Sale> getAllSalesListfromDatetotoDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select * from sale where  created_at>=:fromDate ",nativeQuery = true)
    List<Sale> getAllSalesListfromDate(@Param("fromDate") String fromDate);

    @Query(value = "select * from sale where customer_id=:customerId ",nativeQuery = true)
    List<Sale> getAllSalesListByCustomerId(@Param("customerId") Long customerId);

    List<Sale> findByCustomerId(Long customerId);

    @Query(value = "select * from sale where warehouse_id =:warehouseId and customer_id =:customerId and created_at>=:fromDate and created_at<=:toDate ",nativeQuery = true)
    List<Sale> getByWarehouseIdCustomerIdFromAndToDate(Long warehouseId, Long customerId, String fromDate, String toDate);

    @Query(value = "select * from sale where warehouse_id =:warehouseId and customer_id =:customerId and created_at>=:fromDate",nativeQuery = true)
    List<Sale> getByWarehouseIdCustomerIdAndFromDate(Long warehouseId, Long customerId, String fromDate);

    List<Sale> findByWarehouseIdAndCustomerId(Long warehouseId, Long customerId);

    @Query(value = "select * from sale where customer_id =:customerId and created_at>=:fromDate and created_at<=:toDate",nativeQuery = true)
    List<Sale> getByCustomerAndFromDateAndToDate(Long customerId, String fromDate, String toDate);

    @Query(value = "select * from sale where customer_id =:customerId and created_at>=:fromDate",nativeQuery = true)
    List<Sale> getByCustomerAndFromDate(Long customerId, String fromDate);

    @Query(value = "select * from sale where created_at>=:fromDate and created_at<=:toDate",nativeQuery = true)
    List<Sale> getByFromAndToDate(String fromDate, String toDate);
    @Query(value = "select * from sale where created_at>=:fromDate",nativeQuery = true)
    List<Sale> getByFromDate(String fromDate);
    @Query(value = "select * from sale where created_at<=:toDate",nativeQuery = true)
    List<Sale> getByToDate(String toDate);
    @Query(value = "select * from sale where warehouse_id =:warehouseId and customer_id =:customerId and created_at<=:toDate",nativeQuery = true)
    List<Sale> getByWarehouseIdCustomerIdAndToDate(Long warehouseId, Long customerId, String toDate);
    @Query(value = "select * from sale where warehouse_id =:warehouseId and created_at>=:fromDate and created_at<=:toDate",nativeQuery = true)
    List<Sale> getByWarehouseIdFromAndToDate(Long warehouseId, String fromDate, String toDate);
    @Query(value = "select * from sale where warehouse_id =:warehouseId and created_at>=:fromDate",nativeQuery = true)
    List<Sale> getByWarehouseIdAndFromDate(Long warehouseId, String fromDate);

    List<Sale> findByWarehouseId(Long warehouseId);
    @Query(value = "select * from sale where customer_id =:customerId and created_at<=:toDate",nativeQuery = true)
    List<Sale> getByCustomerAndToDate(Long customerId, String toDate);
}
