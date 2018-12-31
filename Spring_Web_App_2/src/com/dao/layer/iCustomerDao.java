package com.dao.layer;

import java.util.List;

import com.pojo.Customer;

public interface iCustomerDao {

	void addCustomer(Customer customer);
	void updateCustomer(int id);
	Customer getCustomer(int id);
	List<Customer> listCustomers();

}
