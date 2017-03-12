package com.test.dao.impl;

import com.test.dao.DocumentDao;
import com.test.model.Document;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentDaoImpl extends BasicDaoImpl<Document> implements DocumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public DocumentDaoImpl(Class<Document> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Document> findByName(String name) {
        return (List<Document>) sessionFactory.getCurrentSession().
                createQuery("from documents as c where c.name = ?").
                setParameter(0, name).list();
    }

    public Document addDocument(Document document) {
        sessionFactory.getCurrentSession().save(document);
        return document;
    }
}
