package com.omglobal.omglobal.controller;

import com.omglobal.omglobal.model.entities.Files;
import com.omglobal.omglobal.model.response.FileResponse;
import com.omglobal.omglobal.service.FileService;
import com.omglobal.omglobal.utility.enums.FileCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @PostMapping("/uploadFile")
    public FileResponse uploadFile(@Valid @RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(required = true) Long userId,
                                   @RequestParam(required = true) String role, @RequestParam(required = true) FileCategory fileCategory) {
        return fileService.storeFile(file, userId, role, fileCategory);
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@Valid @PathVariable(required = true) Long fileId, HttpServletRequest request) {
        // Load file as Resource
        Files files = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"")
                .body(new ByteArrayResource(files.getData()));
    }


    //@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','SUPPLIER')")
    @GetMapping("/getAllFile")
    public List<FileResponse> allFiles(@RequestParam(value = "userId", required = true) Long userId, @RequestParam(value = "role", required = true) String role){
        return fileService.getAllFile(userId, role);
    }
//    @PostMapping("/uploadMultipleFiles")
//    public List<FileResponse> uploadMultipleFiles(@Valid@RequestParam("files") MultipartFile[] files, @RequestParam Long userId,
//                                                  @RequestParam String role, @RequestParam FileCategory fileCategory) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file, userId, role, fileCategory))
//                .collect(Collectors.toList());
//    }
}

