package com.khasanov.dao;

import com.khasanov.entity.Customer;
import org.hibernate.Session;

public class CustomerDAO extends AbstractDAO<Customer> {
    public CustomerDAO(Session session) {
        super(Customer.class, session);
    }
}