package com.dao.layer;

import com.pojo.Admin;

public interface iUserDAO {
	public String doHibernateLogin(String username, String password);
	public String doHibernateSignUp(Admin admin);
	public Admin getAdminById(String id);
}
