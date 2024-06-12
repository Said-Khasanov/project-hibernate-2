package com.khasanov.dao;

import com.khasanov.entity.Actor;
import org.hibernate.Session;

public class ActorDAO extends AbstractDAO<Actor> {
    public ActorDAO(Session session) {
        super(Actor.class, session);
    }
}
