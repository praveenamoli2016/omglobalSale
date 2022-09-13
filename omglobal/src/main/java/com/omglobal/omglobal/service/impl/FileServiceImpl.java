package com.omglobal.omglobal.service.impl;

import com.omglobal.omglobal.exception.FileNotFoundException;
import com.omglobal.omglobal.exception.FileStorageException;
import com.omglobal.omglobal.model.entities.Files;
import com.omglobal.omglobal.model.response.FileResponse;
import com.omglobal.omglobal.repository.CustomerRepo;
import com.omglobal.omglobal.repository.FileRepo;
import com.omglobal.omglobal.service.FileService;
import com.omglobal.omglobal.utility.enums.FileCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public FileResponse storeFile(MultipartFile file, Long userId, String role, FileCategory fileCategory) {

            // Normalize file name
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            try {
                // Check if the file's name contains invalid characters
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                Files files = null;
                Optional<Files> filesOptional = fileRepo.findByUserIdAndUserRoleAndFileCategory(userId, role, fileCategory);
                if (filesOptional.isPresent()){
                    files = filesOptional.get();
                    files.setData(file.getBytes());
                    files.setFileName(fileName);
                    files.setFileType(file.getContentType());
                } else {
                    files = new Files(fileName, file.getContentType(), file.getBytes(), userId, role, fileCategory);
                }
                Files savedFile = fileRepo.save(files);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(savedFile.getId().toString())
                        .toUriString();
                return new FileResponse(savedFile.getFileName(), fileDownloadUri,
                        file.getContentType(), fileCategory);

            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            } catch (Exception e) {
                throw  new FileStorageException("Error in saving data. Error "+ e.getMessage());
            }
    }

    @Override
    public Files getFile(Long fileId) {
        return fileRepo.findById(fileId)
                .orElseThrow(()->new FileNotFoundException("File not found with Id "+fileId));
    }

    @Override
    public List<FileResponse> getAllFile(Long userId, String role) {
        List<Files> filesList =  fileRepo.findByUserIdAndUserRole(userId, role);
        List<FileResponse> fileResponseList = new ArrayList<>();
        filesList.forEach(files -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(files.getId().toString())
                    .toUriString();
            fileResponseList.add(new FileResponse(files.getFileName(), fileDownloadUri, files.getFileType(), files.getFileCategory()));
        });
        return fileResponseList;
    }
}
