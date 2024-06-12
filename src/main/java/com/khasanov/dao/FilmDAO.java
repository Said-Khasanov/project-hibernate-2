package com.khasanov.dao;

import com.khasanov.entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FilmDAO extends AbstractDAO<Film> {
    public FilmDAO(Session session) {
        super(Film.class, session);
    }

    public Film getFirstAvailableForRentFilm() {
        Query<Film> query = session.createQuery(
                "SELECT f FROM Film f WHERE f.id NOT IN " +
                        "(SELECT DISTINCT i.film.id FROM Inventory i)",
                Film.class
        );
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
