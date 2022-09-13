package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.model.request.dto.CustomerRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRequest {
    List<CustomerRequestDTO> customerRequestDTOS;
}
