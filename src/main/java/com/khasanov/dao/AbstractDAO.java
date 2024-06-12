package com.khasanov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractDAO<T> {
    private final Class<T> entityType;
    protected final Session session;

    public AbstractDAO(Class<T> entityType, Session session) {
        this.entityType = entityType;
        this.session = session;
    }

    public T getById(Long id) {
        return session.get(entityType, id);
    }

    public List<T> getItems(int firstResult, int maxResults) {
        Query<T> query = session.createQuery("from " + entityType.getName(), entityType);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    public List<T> findAll() {
        Query<T> query = session.createQuery("from " + entityType.getName(), entityType);
        return query.list();
    }

    public T save(T entity) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return entity;
    }

    public T update(T entity) {
        Transaction transaction = session.beginTransaction();
        try {
            session.refresh(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return entity;
    }

    public void delete(T entity) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deleteById(Long id) {
        Transaction transaction = session.beginTransaction();
        try {
            T object = getById(id);
            session.delete(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
