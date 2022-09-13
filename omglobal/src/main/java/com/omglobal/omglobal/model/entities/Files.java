package com.omglobal.omglobal.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.omglobal.omglobal.utility.enums.FileCategory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column (name = "user_id")
    private Long userId;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "file_category")
    @Enumerated(EnumType.STRING)
    private FileCategory fileCategory;

    public Files() {
    }

    public Files(String fileName, String fileType, byte[] data, Long userId, String userRole, FileCategory fileCategory) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.userId = userId;
        this.userRole = userRole;
        this.fileCategory = fileCategory;
    }

    public Files(Long id, String fileName, String fileType, byte[] data, Long userId, String userRole, FileCategory fileCategory) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.userId = userId;
        this.userRole = userRole;
        this.fileCategory = fileCategory;
    }
}