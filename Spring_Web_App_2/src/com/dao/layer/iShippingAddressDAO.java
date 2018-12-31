package com.dao.layer;

import java.util.List;

import com.pojo.ShippingAddress;

public interface iShippingAddressDAO {
	
	  void addShippingAddress(ShippingAddress shippingAddress);
	
	  void deleteShippingAddress(int id);
	  
	  ShippingAddress getShippingAddress(int id);
	  
	  List<ShippingAddress> getAllShippingAdresses();

}
