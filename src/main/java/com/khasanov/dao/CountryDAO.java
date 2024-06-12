package com.khasanov.dao;

import com.khasanov.entity.Country;
import org.hibernate.Session;

public class CountryDAO extends AbstractDAO<Country> {
    public CountryDAO(Session session) {
        super(Country.class, session);
    }
}
