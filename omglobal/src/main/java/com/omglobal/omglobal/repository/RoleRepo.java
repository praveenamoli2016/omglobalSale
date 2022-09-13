package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

  //  @Query(value = "SELECT * FROM role ",nativeQuery = true)
    List<Role> findByRoleName(String roleName);

  Optional<Role> findByRoleNameAndStoreId(String customer, Long store_id);
}
