package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Long> {
}
