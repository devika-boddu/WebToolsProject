package com.webtools.finalProject.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Pojo.UserProductMap;
import com.webtools.finalProject.Pojo.UserWishlistMap;

public class UserWishlistDao extends DAO {
	public UserWishlistMap create(UserWishlistMap userWishlist) {
        try {
            //save user object in the database
        	begin();
        	getSession().persist(userWishlist);
        	commit();
        	close();
        	
        	
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            e.printStackTrace();
            
            
        }
        return userWishlist;
    }
	public List<UserWishlistMap> getAllUserWishlistItems(User user){
		System.out.println("userId inside UserProductMap: " + user);
		List<UserWishlistMap> userWishlistItems = null;
		try {
			begin();
			System.out.println("Getting all the UserProducts added to the cart: -----");
			Query<UserWishlistMap> query = getSession().createQuery("FROM UserWishlistMap where user = :user", UserWishlistMap.class);
			query.setParameter("user", user);
			userWishlistItems = query.list();
			System.out.println("userProducts from DB"+ userWishlistItems);
			commit();
			close();
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return userWishlistItems;
		
	}
}
