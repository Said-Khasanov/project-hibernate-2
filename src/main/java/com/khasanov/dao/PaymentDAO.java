package com.khasanov.dao;

import com.khasanov.entity.Payment;
import org.hibernate.Session;

public class PaymentDAO extends AbstractDAO<Payment> {
    public PaymentDAO(Session session) {
        super(Payment.class, session);
    }
}
