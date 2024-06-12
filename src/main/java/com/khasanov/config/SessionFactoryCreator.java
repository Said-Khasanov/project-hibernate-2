package com.khasanov.config;

import com.khasanov.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryCreator {
    private static SessionFactoryCreator instance;
    private final SessionFactory sessionFactory;
    private static Session session;

    private SessionFactoryCreator() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new SessionFactoryCreator();
        }
        return instance.sessionFactory;
    }

    public static Session getSession() {
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

}