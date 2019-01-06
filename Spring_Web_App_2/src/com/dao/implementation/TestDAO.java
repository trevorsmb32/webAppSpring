package com.dao.implementation;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.controller.shoppingCartController;
import com.dao.layer.iCustomerDao;
import com.pojo.Customer;
import com.pojo.Item;
import com.pojo.Order;
import com.pojo.Product;
import com.pojo.ShippingAddress;
import com.pojo.ShoppingCart;

public class TestDAO {

	private static final Logger logger = Logger.getLogger(TestDAO.class);



	@Test()
	public void createProduct() {

		logger.debug("Product");
		Product p = new Product();

		p.setName("Test");


		logger.debug("Hibernate Database connectivity starting ");
		SessionFactory SessionFactory = HibernateUtil2.buildSessionFactory();
		Session session = SessionFactory.openSession();
		session.beginTransaction();
		session.save(p);

		session.getTransaction().commit();
		session.flush();
		logger.debug("Hibernate Database connectivity finished ");



	}

	//@Test
	public void addCustomer() {

		ShippingAddress SA = new ShippingAddress("Test_DAO","Test_DAO","Test_DAO","Test_DAO","Test_DAO");
		Customer customer2 = new Customer("Test_DAO","Test_DAO","Test_DAO","Test_DAO",SA, null);
		
		iCustomerDao dao = new CustomerDao();
		ShippingAddressDAO sdao = new ShippingAddressDAO();
		

		
		dao.addCustomer(customer2);
		//sdao.addShippingAddress(SA);
	}
	
	@Test()
	public void testShoppingCart() {
		
		ShippingAddress SA = new ShippingAddress("Test_DAO","Test_DAO","Test_DAO","Test_DAO","Test_DAO");
		Customer customer2 = new Customer("Test_DAO2","Test_DAO2","Test_DAO2","Test_DAO2",SA, null);
		
		iCustomerDao dao = new CustomerDao();
		dao.addCustomer(customer2);
		logger.debug("Customer Added");
		
		
		logger.debug("Initalize Shopping Cart");
		ShoppingCart sc = new ShoppingCart();

		logger.debug("Creating Items");
		Item item = new Item ("ItemName1", 3, 5,sc);
		Item item2 = new Item ("ItemName2", 3, 5,sc);
		Item item3 = new Item ("ItemName3", 3, 5,sc);

		logger.debug("Adding Item to Cart");
		sc.addToCart(item);
		sc.addToCart(item2);
		sc.addToCart(item3);

		Order order = new Order();
		order.createOrder(sc,customer2);
	

		logger.debug("########### ");
		logger.info(order.toString()+"ShoppingCart:"+sc.gettotal());

		logger.debug("Hibernate Database connectivity starting ");
		SessionFactory SessionFactory = HibernateUtil2.buildSessionFactory();
		Session session = SessionFactory.openSession();
		session.beginTransaction();
		session.save(order);

		session.getTransaction().commit();
		session.flush();
		logger.debug("Hibernate Database connectivity finished ");



	}
}
