package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
}
