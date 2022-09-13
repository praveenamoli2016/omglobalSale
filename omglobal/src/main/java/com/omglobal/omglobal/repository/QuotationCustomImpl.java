package com.omglobal.omglobal.repository;

import com.omglobal.omglobal.model.entities.Quotation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuotationCustomImpl implements QuotationCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Quotation> findCustomNativeQuery(String query) {
        TypedQuery<Quotation> q=em.createQuery(query,Quotation.class);
        return q.getResultList();
    }
}
