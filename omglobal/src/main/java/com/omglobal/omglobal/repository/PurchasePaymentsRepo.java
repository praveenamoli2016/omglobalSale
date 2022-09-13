package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.PurchasePayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePaymentsRepo extends JpaRepository<PurchasePayments,Long> {
}
