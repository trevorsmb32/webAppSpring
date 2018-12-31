package com.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import com.controller.shoppingCartController;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(name="total")
	double total;
	
	@OneToMany(mappedBy="shoppingCart")
	List<Item> items = new ArrayList<Item>();
	
	@OneToOne(fetch=FetchType.LAZY,targetEntity = Order.class)
	@JoinColumn(name="order_id")
	Order order;

	private static final Logger logger = Logger.getLogger(shoppingCartController.class);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double gettotal() {
		return total;
	}

	public void settotal(double total) {
		this.total = total;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


	public void addToCart(Item item) {
		logger.info("Before Attempting to add Item: "+item.getName());
		boolean isExist = false;
		double totalNumber=0;
		for (Item c : items) {
			//if (c.getName()==item.getName()) {
			if (c.equals(item)){
				logger.info("Name of item is "+c.getName()+"\n the added item name is:" +item.getName());
				c.setQuantity((int) (c.getQuantity() + 1));
				logger.info("Increasing:"+item.getName()+" to"+c.getQuantity());
				isExist = true;
			}
			totalNumber = totalNumber+c.getQuantity()*c.getPrice();
			logger.info("Add to Looping: "+totalNumber);

		}
		if (isExist == false) {
			logger.info("The Item: "+item.getName()+" does not exists adding item");
			items.add(item);
			totalNumber = totalNumber+item.getQuantity()*item.getPrice();
			logger.info("Item does not exist total: "+totalNumber);

		}
		this.total=totalNumber;

	}

	public void deleteFromCart(int id) {
		Iterator <Item> itr = items.iterator();
		while (itr.hasNext()){
			Item item = itr.next();
			logger.info("Looping through item found: "+item.getName());
			if (item.getId() == (id)) {
				logger.info("Removing "+item.getName());
				this.total=this.total-(item.getQuantity()*item.getPrice());
				logger.info("This.total after subtraction: "+this.total);
				itr.remove();
			}
		}

	}

	public void editCart(int id,int quantity) {
		double totalNumber=0;
		for (Item c : items) {
			if (c.getId() == (id)) {
				c.setQuantity(quantity);
				logger.info("Quantity has been updated to: "+c.getQuantity());
			}
			logger.info("Loop: "+c.getId());
			logger.info("Quantity is now "+c.getQuantity());
			logger.info("Price is now "+c.getPrice());
			totalNumber = totalNumber+c.getQuantity()*c.getPrice();
			logger.info("Price is now "+totalNumber);
		}
		this.total=totalNumber;
	}



}



