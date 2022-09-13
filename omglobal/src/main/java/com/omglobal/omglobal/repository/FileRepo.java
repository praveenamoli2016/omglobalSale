package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Files;
import com.omglobal.omglobal.utility.enums.FileCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<Files,Long> {
    Optional<Files> findByUserIdAndUserRoleAndFileCategory(Long userId, String role, FileCategory fileCategory);

    List<Files> findByUserIdAndUserRole(Long userId, String role);
}
