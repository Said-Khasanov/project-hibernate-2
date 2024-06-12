package com.khasanov.dao;

import com.khasanov.entity.Staff;
import org.hibernate.Session;

public class StaffDAO extends AbstractDAO<Staff> {
    public StaffDAO(Session session) {
        super(Staff.class, session);
    }
}
