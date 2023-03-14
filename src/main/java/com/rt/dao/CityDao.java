package com.rt.dao;

import java.util.List;


import org.hibernate.cfg.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import com.rt.model.City;
import com.rt.model.Customer;

import java.util.List;

public class CityDao {
	
	private Session session;
	private SessionFactory factory;
	
	public void openCitySession() {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        this.factory = cfg.buildSessionFactory();
        this.session = factory.openSession();
	}
	
	public void closeCitySession() {
		session.close();
        factory.close();
	}
	
    public List<City> getAllCities() {
    	openCitySession();
	    Transaction tx = null;
        List<City> cities = null;
        
        try {
        	tx = session.beginTransaction();
	        cities = session.createQuery("FROM City", City.class).list();
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        closeCitySession();
	    }
        
        return cities;
    }

}