package com.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.layer.iOrder;
import com.pojo.Order;
import com.pojo.Product;

public class OrderDAO implements iOrder {

	public void addOrder(Order order){		
		SessionFactory SessionFactory = HibernateUtil2.buildSessionFactory();
		Session session = SessionFactory.openSession();
		session.beginTransaction();
		session.save(order);

		session.getTransaction().commit();
		session.flush();
	}

	public void deleteOrder(int id){
		SessionFactory SessionFactory = HibernateUtil2.buildSessionFactory();
		Session session = SessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(id);
		
	}

	public Order getOrderById(int id){
		SessionFactory SF = HibernateUtil2.buildSessionFactory();
		Session session = SF.getCurrentSession();
		session.beginTransaction();
		Order order = (Order) session.get(Order.class,id);
		return order;		
		
		
	}

	public List<Order> getAllOrders(){
		SessionFactory SF = HibernateUtil2.buildSessionFactory();
		Session session = SF.getCurrentSession();
		List<Order> orders = session.createQuery("From Order").list();

		return orders;
		
	}





}
