package com.datx.repository;

import java.io.Serializable;
import java.util.List;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

/**
 * Created by Soudeh Masoudian on 11/30/2016.
 */
public interface IGeneralDao<T, PK extends Serializable> {
    void saveOrUpdate(T t) throws Exception;
    void saveOrUpdate(List<T> t) throws Exception;
    void delete(PK id) throws Exception;
    T findOne(PK id) throws Exception;
    List<T> findAll() throws Exception;
    long count() throws Exception;
}
