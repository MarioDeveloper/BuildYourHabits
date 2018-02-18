package com.besthabit.product.module.web.test;


import com.besthabit.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

public class CreateOneTimeEvent {

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
                // start a transaction
                session.beginTransaction();

                int userId = 4;
                User tempUser = session.get(User.class, userId);

                OneTimeEvent oneTimeEvent = new OneTimeEvent("Idziemy na basen!", "łatwe", LocalDateTime.now(), 10, -5);

                tempUser.addOneTimeEvent(oneTimeEvent);

                session.save(oneTimeEvent);

                // commit transaction
                session.getTransaction().commit();

                System.out.println("Done!");

            } finally {
                factory.close();
            }


        }

}
