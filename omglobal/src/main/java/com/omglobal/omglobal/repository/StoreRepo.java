package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
}
