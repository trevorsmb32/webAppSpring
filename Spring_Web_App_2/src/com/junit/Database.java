package com.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dao.implementation.CustomerDao;
import com.dao.implementation.RolesDAO;
import com.pojo.Customer;
import com.pojo.ShippingAddress;

public class Database {

	@Test
	public void TestRoles(){

		
		ShippingAddress sa = new ShippingAddress("a","b","c","d","e");
		RolesDAO rolesDB = new RolesDAO();
		rolesDB.addRole(role);
		Customer customer = new Customer("First", "Second", "email", "password",sa);
		CustomerDao customerdb = new CustomerDao();
		customerdb.addCustomer(customer);

		
	}

}
