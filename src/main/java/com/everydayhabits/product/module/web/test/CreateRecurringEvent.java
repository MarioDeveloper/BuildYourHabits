package com.everydayhabits.product.module.web.test;

import com.everydayhabits.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

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
                .addAnnotatedClass(OneTimeEvent.class)
                .buildSessionFactory();


        // create session
        Session session = factory.getCurrentSession();

        try {

            LocalDateTime tempDate = LocalDateTime.now();

            tempDate.plusMonths(1);

            LocalDateTime start_date_and_first_planned_date = LocalDateTime.now();

            // start a transaction
            session.beginTransaction();

            int userId = 4;
            User tempUser = session.get(User.class, userId);

            RecurringEvent recurringEvent = new RecurringEvent(2, "minuty", "Siłownia", "Idziemy do Platinium", "Hard", start_date_and_first_planned_date, tempDate);

            RealizationRecurringEvent realizationRecurringEvent = new RealizationRecurringEvent(start_date_and_first_planned_date, 10,-5);

            tempUser.addRecuuringEvent(recurringEvent);

            recurringEvent.addRealizationRecurringEvent(realizationRecurringEvent);



            session.save(recurringEvent);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }

}


