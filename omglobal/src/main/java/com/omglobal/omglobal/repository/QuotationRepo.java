package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepo extends JpaRepository<Quotation,Long>, QuotationCustom {

}
