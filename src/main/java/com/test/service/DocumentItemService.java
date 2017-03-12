package com.test.service;

import com.test.model.DocumentItem;

import java.util.List;

public interface DocumentItemService {
    /**
     * Create documentItem at database
     *
     * @param documentItem - documentItem for creation
     * @return documentItem
     */
    DocumentItem addDocumentItem(DocumentItem documentItem);

    /**
     * Receive documentItem from database
     *
     * @param id - documentItem's id what we want to receive
     * @return documentItem
     */
    DocumentItem getDocumentItemById(long id);

    /**
     * Receive documentItem from database with field: name
     *
     * @param name - documentItem's field name
     * @return list of documentItem with field equals name param
     */
    List<DocumentItem> getDocumentItemByName(String name);

    /**
     * Receive all documentItem from database
     *
     * @return all documentItem from database
     */
    List<DocumentItem> getDocumentItemList();

    /**
     * Delete documentItem from database with specify id
     *
     * @param id - documentItem's unique id
     * @return body of deleted documentItem
     */
    DocumentItem deleteDocumentItem(long id);

    /**
     * Update documentItem
     *
     * @param documentItem - documentItem from request for future updating
     * @return updated documentItem
     */
    DocumentItem updateDocumentItem(DocumentItem documentItem);
}
