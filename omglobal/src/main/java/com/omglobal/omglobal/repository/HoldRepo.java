package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Hold;
import com.omglobal.omglobal.model.entities.SalesPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldRepo extends JpaRepository<Hold,Long> {

}
