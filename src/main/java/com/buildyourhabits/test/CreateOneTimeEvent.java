package com.buildyourhabits.test;


import com.buildyourhabits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

                OneTimeEvent oneTimeEvent = new OneTimeEvent();

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
