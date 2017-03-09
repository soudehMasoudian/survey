package com.datx.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/30/2016.
 */
public abstract class AbstractDao<T, PK extends Serializable> implements IGeneralDao<T, PK>  {
    Class<T> type;
    public Session session;

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    protected void getSession(){
        session = sessionFactory.getCurrentSession();
    }

    protected void openSessuion(){
        session = sessionFactory.openSession();
    }

    public void saveOrUpdate(T t) throws Exception {
        getSession();
        session.saveOrUpdate(t);
        session.flush();
    }

    public void merge(T t) throws Exception {
        getSession();
        session.merge(t);
        session.flush();
    }

    public void saveOrUpdate(List<T> t) throws Exception {
        getSession();
        for (T obj : t) {
            session.saveOrUpdate(obj);
        }
    }

    public void delete(PK id)  throws Exception {
        getSession();
        session.delete(findOne(id));
    }

    public T findOne(PK id) throws Exception {
        getSession();
        return (T) session.get(type.getName(), id);
    }

    public List<T> findAll() throws Exception {
        getSession();
        return session.
                createCriteria(type.getName()).
                list();
    }

    /**
     * count data.
     * @return
     */
    public long count() throws Exception {
        getSession();
        return (Long) session.
                createCriteria(type.getName()).
                setProjection(Projections.rowCount()).
                uniqueResult();
    }
}
