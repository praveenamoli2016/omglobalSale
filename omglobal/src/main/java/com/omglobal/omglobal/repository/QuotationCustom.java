package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Quotation;

import java.util.List;

public interface QuotationCustom {
    List<Quotation> findCustomNativeQuery(String query);
}
