package com.webtools.finalProject.Dao;

import org.springframework.stereotype.Component;

import com.webtools.finalProject.Dao.DAO;
import com.webtools.finalProject.Pojo.Address;

public class AddressDao extends DAO{

    public void save(Address a) {
        begin();
        getSession().persist(a);
        commit();
    }
    
    public void update(Address a) {
        try {
            begin();
            getSession().merge(a);
            commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(Address a) {
        begin();
        getSession().remove(a);
        commit();
    }
    
    public Address address(int aid) {
        Address address = getSession().get(Address.class, aid);
        return address;
    }
}