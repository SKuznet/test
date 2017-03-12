package com.test.service;

import com.test.model.Document;

import java.util.List;

public interface DocumentService {

    /**
     * Create document at database
     *
     * @param document - document for creation
     * @return document
     */
    Document addDocument(Document document);

    /**
     * Receive document from database
     *
     * @param id - document's id what we want to receive
     * @return document
     */
    Document getDocumentById(long id);

    /**
     * Receive document from database with field: name
     *
     * @param name - document's field name
     * @return list of documents with field equals name param
     */
    List<Document> getDocumentByName(String name);

    /**
     * Receive all documents from database
     *
     * @return all documents from database
     */
    List<Document> getDocumentList();

    /**
     * Delete document from database with specify id
     *
     * @param id - document's unique id
     * @return body of deleted document
     */
    Document deleteDocument(long id);

    /**
     * Update document
     *
     * @param document - document from request for future updating
     * @return updated document
     */
    Document updateDocument(Document document);
}

