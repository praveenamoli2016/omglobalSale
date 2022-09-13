package com.omglobal.omglobal.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
//import com.omglobal.omglobal.model.entities.DatabaseFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String status;
    private String code;
    private String message;
    private Object response;

}
