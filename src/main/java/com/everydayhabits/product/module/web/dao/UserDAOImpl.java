package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.entity.Level;
import com.everydayhabits.product.module.web.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User saveUser(User theUser) {

        Session currentSession = sessionFactory.getCurrentSession();

        theUser.setLevel(getLevelById(1));

        currentSession.save(theUser);

        String userEmail = theUser.getEmail();

        return getUserByEmail(userEmail);
    }

    @Override
    public User getUserByEmail(String email) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        // get the customer by primary keyx
        theQuery = currentSession.createQuery("from User where email=:userEmail");
        theQuery.setParameter("userEmail", email);

        // execute query and get result list
        User user = (User) theQuery.uniqueResult();

        return user;
    }

    @Override
    public User findByEmail(String email) {

        return getUserByEmail(email);
    }


    @Override
    public Level getLevelById(int levelId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Level theLevel = currentSession.get(Level.class, levelId);

        return theLevel;
    }
}
