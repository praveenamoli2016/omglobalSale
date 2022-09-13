package com.omglobal.omglobal.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.omglobal.omglobal.utility.enums.FileCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private FileCategory fileCategory;
}
