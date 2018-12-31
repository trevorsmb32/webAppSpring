package com.dao.layer;

import java.util.List;

import com.pojo.Product;


public interface iProductDAO {

	void addProduct(Product product);

	void editProduct(int id);

	Product getProductById(int id);

	List<Product> getAllProducts();

	void deleteProduct(int id);

}

