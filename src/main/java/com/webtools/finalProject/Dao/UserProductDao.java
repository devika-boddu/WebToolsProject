package com.webtools.finalProject.Dao;

import org.hibernate.HibernateException;

import com.webtools.finalProject.Pojo.UserProductMap;



public class UserProductDao extends DAO{
	public UserProductMap create(UserProductMap userProduct) {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(userProduct);
        	commit();
        	close();
        	
        	
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            e.printStackTrace();
            
            
        }
        return userProduct;
    }
}
