package com.dao.implementation;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.layer.iCustomerDao;
import com.pojo.Customer;

public class CustomerDao implements iCustomerDao{

	private static final Logger logger = Logger.getLogger(CustomerDao.class);
	
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
	public Customer getCustomer(String email) {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String sql = "FROM Customer C WHERE C.email = :email";
		org.hibernate.Query query =  session.createQuery(sql);
		query.setParameter("email", email);
		logger.info("----------"
				+ "Execute query: "+query);
		logger.info("uniqueResult: "+query.uniqueResult());

		return (Customer) query.uniqueResult();

	}

	@Override
	public List<Customer> listCustomers() {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		List<Customer> customers = session.createSQLQuery("From Customer ").list();
		session.getTransaction().commit();

		return customers;
	}

}
