package com.khasanov.dao;

import com.khasanov.entity.City;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(Session session) {
        super(City.class, session);
    }

    public City getFirstByName(String name) {
        Query<City> query = session.createQuery("FROM City c WHERE c.city=:city", City.class);
        query.setParameter("city", name);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}