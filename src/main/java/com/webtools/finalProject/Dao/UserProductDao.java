package com.webtools.finalProject.Dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.webtools.finalProject.Pojo.User;
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
	
	public List<UserProductMap> getAllUserProducts(User user){
		System.out.println("userId inside UserProductMap: " + user);
		List<UserProductMap> userProducts = null;
		try {
			begin();
			System.out.println("Getting all the UserProducts added to the cart: -----");
			Query<UserProductMap> query = getSession().createQuery("FROM UserProductMap where user = :user", UserProductMap.class);
			query.setParameter("user", user);
			userProducts = query.list();
			System.out.println("userProducts from DB"+ userProducts);
			commit();
			close();
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return userProducts;
		
	}
	
	public void deleteAllTravelPackages(){
		try {
			begin();
			String hql = "DELETE FROM UserProductMap";
			Query<UserProductMap> query = getSession().createQuery(hql);
			query.executeUpdate();
			commit();
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		}
	}






	
	
}
