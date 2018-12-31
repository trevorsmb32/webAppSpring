package com.dao.layer;

import java.util.List;

import com.pojo.Order;


public interface iOrder {

	void addOrder(Order order);

	Order getOrderById(int id);

	List<Order> getAllOrders();

	void deleteOrder(int id);
	
}
