package com.test.service.impl;

import com.test.dao.DocumentItemDao;
import com.test.model.DocumentItem;
import com.test.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("documentItemService")
public class DocumentItemServiceImpl implements DocumentItemService {
    @Autowired
    DocumentItemDao documentItemDao;

    @Override
    public DocumentItem addDocumentItem(DocumentItem documentItem) {
        return documentItemDao.create(documentItem);
    }

    @Override
    public DocumentItem getDocumentItemById(long id) {
        return documentItemDao.getById(id);
    }

    @Override
    public List<DocumentItem> getDocumentItemByName(String name) {
        return documentItemDao.findByName(name);
    }

    @Override
    public List<DocumentItem> getDocumentItemList() {
        return documentItemDao.getList();
    }

    @Override
    public DocumentItem deleteDocumentItem(long id) {
        return documentItemDao.delete(getDocumentItemById(id));
    }

    @Override
    public DocumentItem updateDocumentItem(DocumentItem documentItem) {
        return null;
    }
}
