package com.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.layer.iItemDAO;
import com.pojo.Item;

public class ItemDAO implements iItemDAO {

	public void addItem(Item item){
		SessionFactory sf = HibernateUtil2.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(item);
	}
	
}
