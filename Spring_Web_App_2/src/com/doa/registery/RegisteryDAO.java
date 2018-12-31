package com.doa.registery;


import com.dao.implementation.userDAO;
import com.dao.layer.iUserDAO;

public class RegisteryDAO {

	public static iUserDAO userDAO;
	
	static{
		userDAO = new userDAO();
	}


	public static void setuserDAO(iUserDAO userDAO) {
		RegisteryDAO.userDAO = userDAO;
	}
	
	public static iUserDAO getUserDAO() {
		return userDAO;
	}

	
}