package com.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.layer.iShippingAddressDAO;
import com.models.HibernateUtil;
import com.pojo.ShippingAddress;

public class ShippingAddressDAO implements iShippingAddressDAO {

	@Override
	public void addShippingAddress(ShippingAddress sa) {		
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(sa);
		session.getTransaction().commit();
		sf.close();
		
	}

	@Override
	public void deleteShippingAddress(int id) {
		SessionFactory sf =HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(id);
		
	}

	@Override
	public ShippingAddress getShippingAddress(int id) {
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ShippingAddress sa = (ShippingAddress) session.get(ShippingAddress.class,id);
		
		return sa;
	}

	@Override
	public List<ShippingAddress> getAllShippingAdresses() {
			SessionFactory sf = HibernateUtil2.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			
			List<ShippingAddress> sa = session.createQuery("From ShippingAddress").list();
			
		return sa;
	}

}
