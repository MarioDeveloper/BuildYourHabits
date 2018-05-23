package com.everydayhabits.product.module.web.test;

import com.everydayhabits.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddRewardToUser {

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

//            // start a transaction
//            session.beginTransaction();
//
//            int userId = 4;
//            User tempUser = session.get(User.class, userId);
//
//            System.out.println("\nLoaded user: " + tempUser);
//            System.out.println("Rewards: " + tempUser.getRewards());
//
//            int rewardId = 1;
//            Reward tempReward = session.get(Reward.class, rewardId);
//
//            System.out.println("\nLoaded reward: " + tempReward);
//
//            tempUser.addReward(tempReward);
//
//            System.out.println("\nSaving the courses...");
//
//            session.save(tempUser);
//
//            // commit transaction
//            session.getTransaction().commit();
//
//            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }

}


