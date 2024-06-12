package com.khasanov.dao;

import com.khasanov.entity.Rental;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(Session session) {
        super(Rental.class, session);
    }

    public Rental getFirstUnreturnedRental() {
        Query<Rental> query = session.createQuery("FROM Rental r WHERE r.returnDate IS NULL", Rental.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
