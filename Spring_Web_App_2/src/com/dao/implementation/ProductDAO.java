package com.dao.implementation;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.controller.productController;
import com.dao.layer.iProductDAO;
import com.pojo.Product;

@Repository
public class ProductDAO implements  iProductDAO{

	private static final Logger logger = Logger.getLogger(ProductDAO.class);
	
	@Override
	public void addProduct(Product product) {
		SessionFactory sessionFactory = HibernateUtil2.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(product);
		session.getTransaction().commit();
		// Close resources
		sessionFactory.close();
		
	}

	public void editProduct(int id) {
		Session session = HibernateUtil2.getSessionFactory().openSession();
		session.beginTransaction();
		Product product = (Product) session.get(Product.class,id);
		session.update(product);
		session.getTransaction().commit();
		//session.close();
		session.flush();
		
		
	}

	@Override
	public Product getProductById(int id) {
		Session session = HibernateUtil2.getSessionFactory().openSession();
		Product product = (Product) session.get(Product.class,id);
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = HibernateUtil2.getSessionFactory().openSession();
		List<Product> products = session.createQuery("From Product").list();
		logger.debug("Query List Executed"+session.createQuery("From Product").list());
		session.flush();
		
		return products;
	}

	@Override
	public void deleteProduct(int id) {
		Session session = HibernateUtil2.getSessionFactory().openSession();
		 
		session.beginTransaction();
		Product product = (Product) session.get(Product.class,id);
		session.delete(product);
		session.getTransaction().commit();
		//session.close();
		session.flush();
		
	}

}
