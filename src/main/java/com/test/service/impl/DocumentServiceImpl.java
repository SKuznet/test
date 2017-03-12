package com.test.service.impl;

import com.test.dao.DocumentDao;
import com.test.model.Document;
import com.test.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentDao documentDao;

    @Override
    public Document addDocument(Document document) {
        return documentDao.addDocument(document);
    }

    @Override
    public Document getDocumentById(long id) {
        return documentDao.getById(id);
    }

    @Override
    public List<Document> getDocumentByName(String name) {
        return documentDao.findByName(name);
    }

    @Override
    public List<Document> getDocumentList() {
        return documentDao.getList();
    }

    @Override
    public Document deleteDocument(long id) {
        return documentDao.delete(documentDao.getById(id));
    }

    @Override
    public Document updateDocument(Document document) {
        return documentDao.update(document);
    }
}
