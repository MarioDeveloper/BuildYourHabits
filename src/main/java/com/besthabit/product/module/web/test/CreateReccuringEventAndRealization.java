package com.besthabit.product.module.web.test;

import com.besthabit.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class CreateReccuringEventAndRealization {

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

//            int recurringEventId = 4;
//            RecurringEvent recurringEvent = session.get(RecurringEvent.class, recurringEventId);

            int userId = 1;
            User tempUser = session.get(User.class, userId);

            RecurringEvent recurringEvent1 = new RecurringEvent(2,"cos","cos","cos", tempUser);

            System.out.println("\n\n recurringEvent: " + recurringEvent1);

            System.out.println("\n\n realizationRecurringCourses: " + recurringEvent1.getRealizationRecurringEvents());


            RealizationRecurringEvent realizationRecurringEvent = new RealizationRecurringEvent();

            recurringEvent1.addRealizationRecurringEvent(realizationRecurringEvent);

            session.save(recurringEvent1);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }
}
