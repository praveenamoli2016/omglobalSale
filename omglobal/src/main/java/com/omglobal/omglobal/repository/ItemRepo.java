package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {
//   @Query(value = "Select * from item where id in (:itemSet)", nativeQuery = true)
//   List<Item> getAllById(List<Long> itemSet);
}
