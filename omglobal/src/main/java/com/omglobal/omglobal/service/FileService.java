package com.omglobal.omglobal.service;

import com.omglobal.omglobal.model.entities.Files;
import com.omglobal.omglobal.model.response.FileResponse;
import com.omglobal.omglobal.utility.enums.FileCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResponse storeFile(MultipartFile file, Long customerId, String role, FileCategory fileCategory);

    Files getFile(Long fileId);

    List<FileResponse> getAllFile(Long userId, String role);
}
