package com.omglobal.omglobal.model.request;

import com.omglobal.omglobal.model.request.dto.SupplierRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class SupplierRequest {
    List<SupplierRequestDTO> supplierRequestDTOS;
}
