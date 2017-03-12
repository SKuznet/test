package com.test.dao.impl;

import com.test.dao.DocumentItemDao;
import com.test.model.DocumentItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentItemDaoImpl  extends BasicDaoImpl<DocumentItem> implements DocumentItemDao {
    @Autowired
    private SessionFactory sessionFactory;

    public DocumentItemDaoImpl(Class<DocumentItem> entityClass) {
        super(entityClass);
    }

    @Override
    public List<DocumentItem> findByName(String name) {
        return (List<DocumentItem>) sessionFactory.getCurrentSession().
                createQuery("from document_items as c where c.name = ?").
                setParameter(0, name).list();
    }
}
