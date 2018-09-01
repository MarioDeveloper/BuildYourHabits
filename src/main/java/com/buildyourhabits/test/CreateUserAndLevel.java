package com.buildyourhabits.test;

import com.buildyourhabits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateUserAndLevel {

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

            Level tempLevel = new Level("firstAttempt");

            User tempUser = new User("Super", "Mario", "mario@gmail.com", "pass", "Kraków", 100, 100);

            tempUser.setLevel(tempLevel);

            // start a transaction
            session.beginTransaction();

            System.out.println("Saving user: " + tempUser);
            session.save(tempUser);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }

}


