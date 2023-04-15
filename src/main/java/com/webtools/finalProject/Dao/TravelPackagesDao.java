package com.webtools.finalProject.Dao;

import java.sql.Connection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;

@Component
public class TravelPackagesDao extends DAO {
	
	public List<TravelPackages>	 getProducts() throws UserException{
		
		List<TravelPackages> myEntities = null;
		try {
			myEntities = getSession().createQuery("FROM TravelPackages", TravelPackages.class).getResultList();
			System.out.println(myEntities);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
        return myEntities;
    }
	
	public TravelPackages getSelectedProduct(Integer packageId) throws UserException{
			
			List<TravelPackages> myEntities = null;
			TravelPackages travelPackage = null;
			try {
				//myEntities = getSession().createQuery("FROM User where packageId= :packageId", TravelPackages.class).getResultList();
				Query<TravelPackages> query =  getSession().createQuery("FROM TravelPackages where packageId= :packageId", TravelPackages.class);
			    query.setParameter("packageId", packageId);
			    travelPackage = query.uniqueResult();
			    
				System.out.println(travelPackage);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
	        return travelPackage;
	    }
	
	


}
