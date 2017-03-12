package com.test.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Basics method for DAO-child
 */
public interface BasicDao<T> {

    /**
     * @return current Hibernate session
     */
    Session getCurrentSession();

    /**
     * Create entity at database
     *
     * @param entity current entity
     * @return entity
     */
    T create(T entity);

    /**
     * Copy of entity of the specified type on it Id
     *
     * @param id - id of concrete entity
     * @return entity
     */
    T getById(long id);

    /**
     * @return полный список сущностей
     */
    List<T> getList();

    /**
     * Update current entity at database
     *
     * @param entity - entity from request
     * @return updated entity
     */
    T update(T entity);

    /**
     * Delete current entity from database
     *
     * @param entity - entity for delete
     * @return deleted entity
     */
    T delete(T entity);

}
