package com.test.dao;

import com.test.model.DocumentItem;

import java.util.List;

/**
 * DocumentItem DAO
 */
public interface DocumentItemDao extends BasicDao<DocumentItem> {

    /**
     * Find list of DocumentItems at database by name
     *
     * @param name of current DocumentItem
     * @return list of DocumentItems
     */
    List<DocumentItem> findByName(String name);
}
