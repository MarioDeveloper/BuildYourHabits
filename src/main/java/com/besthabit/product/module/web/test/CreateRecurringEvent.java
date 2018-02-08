package com.besthabit.product.module.web.test;

import com.besthabit.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateRecurringEvent {

    public static void main(String[] args) {


        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Level.class)
                .addAnnotatedClass(RecurringEvent.class)
                .addAnnotatedClass(Reward.class)
                .addAnnotatedClass(RealizationRecurringEvent.class)
                .buildSessionFactory();


        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            int userId = 1;
            User tempUser = session.get(User.class, userId);

            RecurringEvent recurringEvent = new RecurringEvent(2,"Siłownia","Idziemy do Platinium", "Hard",tempUser);

            session.save(recurringEvent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }

}


