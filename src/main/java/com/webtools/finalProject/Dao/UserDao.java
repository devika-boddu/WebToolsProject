package com.webtools.finalProject.Dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.User;


public class UserDao extends DAO {
	 
	public void save(User user) throws Exception {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(user);
        	commit();
        } catch (HibernateException e) {
            rollback();       
            e.printStackTrace();
            
            throw new UserException("Exception while creating user: " + e.getMessage());
    }
 }
 
 public User getUser(String name) throws UserException{
        //User user = getSession().get(User.class, username);
	 	try {
	 		Query<User> query =  getSession().createQuery("FROM User where name= :name", User.class);
		    query.setParameter("name", name);
		    User user = query.uniqueResult();
		    System.out.println(user);
	        return user;
	 	}catch (HibernateException e) {
             rollback();
             throw new UserException("Could not get user ", e);
         }
	 	
    }
 	
 public User updateUserPassword(String name, String newPassword) throws UserException{
	 User user = getUser(name);
	 
	 if (user != null) {
	        
		 	begin();
	        user.setPassword(newPassword); // Replace newPassword with the actual new password value
	       getSession().merge(user);
	       System.out.println(user);
	        commit();
	       
	    }
	 return user;
	 
 }
 
 public void delete(User user) throws UserException {
	 try {
         //delete user object in the database
     	begin();
     	getSession().remove(user);
     	commit();
     	close();
     } catch (HibernateException e) {
         rollback();
         throw new UserException("Exception while deleting user: " + e.getMessage());
     }
}
	

}
