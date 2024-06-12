package com.khasanov.dao;

import com.khasanov.entity.Language;
import org.hibernate.Session;

public class LanguageDAO extends AbstractDAO<Language> {
    public LanguageDAO(Session session) {
        super(Language.class, session);
    }
}
