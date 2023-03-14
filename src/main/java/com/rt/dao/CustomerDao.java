package com.rt.dao;

import java.util.List;


import org.hibernate.cfg.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import java.util.List;

import com.rt.model.Customer;

public class CustomerDao {
	
	private Session session;
	private SessionFactory factory;
	
	public void openCustomerSession() {
		Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        this.factory = cfg.buildSessionFactory();
        this.session = factory.openSession();
	}
	
	public void closeCustomerSession() {
		session.close();
        factory.close();
	}
	
	public List<Customer> getCustomers() {
	    openCustomerSession();
	    Transaction tx = null;
	    List<Customer> customers = null;

	    try {
	        tx = session.beginTransaction();
	        customers = session.createQuery("FROM Customer", Customer.class).list();
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        closeCustomerSession();
	    }
	    return customers;
	}
	
	public Customer getCustomerByCustomerId(int customerId) {
		
		openCustomerSession();
		session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
		closeCustomerSession();
		return customer;
	}
}
