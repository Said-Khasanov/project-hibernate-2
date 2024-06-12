package com.khasanov.dao;

import com.khasanov.entity.Category;
import org.hibernate.Session;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(Session session) {
        super(Category.class, session);
    }
}
