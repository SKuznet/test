package com.test.dao;

import com.test.model.Document;

import java.util.List;

/**
 * Document DAO
 */
public interface DocumentDao extends BasicDao<Document> {

    /**
     * Find list of documents at database by name
     *
     * @param name of current Document
     * @return document
     */
    List<Document> findByName(String name);

    /**
     * Create document at database
     *
     * @param document - Document for creation
     * @return document
     */
    Document addDocument(Document document);
}
