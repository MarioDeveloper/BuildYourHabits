package com.besthabit.product.module.web.test;

import com.besthabit.product.module.web.entity.Level;
import com.besthabit.product.module.web.entity.RecurringEvent;
import com.besthabit.product.module.web.entity.Reward;
import com.besthabit.product.module.web.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateUserAndLevelApp {

    public static void main(String[] args) {


        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Level.class)
                .addAnnotatedClass(RecurringEvent.class)
                .addAnnotatedClass(Reward.class)
                .buildSessionFactory();


        // create session
        Session session = factory.getCurrentSession();

        try {

            Level tempLevel = new Level("firstAttempt");

            User tempUser = new User("Mario","Bross","mario@gmail.com","pass",100,100);

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


