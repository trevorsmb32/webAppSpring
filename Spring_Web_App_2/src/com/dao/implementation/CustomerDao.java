package com.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.layer.iCustomerDao;
import com.pojo.Customer;

public class CustomerDao implements iCustomerDao{

	@Override
	public void addCustomer(Customer customer) {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		sf.close();
		
	}

	@Override
	public void updateCustomer(int id) {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class,id);
		session.update(customer);
		session.getTransaction().commit();
		sf.close();
		
	}

	@Override
	public Customer getCustomer(int id) {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class,id);
		session.getTransaction().commit();
		//sf.close();
		
		return customer;
				
	}

	@Override
	public List<Customer> listCustomers() {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		List<Customer> customers = session.createSQLQuery("From Customer").list();
		session.getTransaction().commit();
		
		return customers;
	}

}
