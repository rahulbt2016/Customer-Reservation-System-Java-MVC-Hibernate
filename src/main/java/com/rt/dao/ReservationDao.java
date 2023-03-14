package com.rt.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rt.model.City;
import com.rt.model.Customer;
import com.rt.model.Reservation;

public class ReservationDao {
	
	private Session session;
	private SessionFactory factory;
	
	public void openReservationSession() {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        this.factory = cfg.buildSessionFactory();
        this.session = factory.openSession();
	}
	
	public void closeReservationSession() {
		session.close();
        factory.close();
	}
	
    public List<Reservation> getReservationsByCustomerId(int customerId) {
    	openReservationSession();
	    Transaction tx = null;
        List<Reservation> reservations = null;
        
        try {
        	tx = session.beginTransaction();
        	Query query = session.createQuery("from Reservation where customerId=:customerId");
        	query.setParameter("customerId", customerId);
	        reservations = query.getResultList();
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        closeReservationSession();
	    }
        
        return reservations;
    }
    
    public void addReservation(Reservation reservation, int customerId, String originCityCode, String destinationCityCode) {
        openReservationSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        City originCity = session.get(City.class, originCityCode);
        City destinationCity = session.get(City.class, destinationCityCode);
        reservation.setCustomer(customer);
        reservation.setOriginCity(originCity);
        reservation.setDestinationCity(destinationCity);
        session.save(reservation);
        session.getTransaction().commit();
        closeReservationSession();
    }

}
