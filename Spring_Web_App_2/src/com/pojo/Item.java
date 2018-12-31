package com.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Item")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int quantity;
	private double price;
	
	@ManyToOne
    @JoinColumn(name="shoppingCart_id", nullable=false)
	private ShoppingCart shoppingCart;


	public Item(){
		
	}
	
	public Item(String name, int quantity, int price,ShoppingCart shoppingCart) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.shoppingCart=shoppingCart;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setQuantity(int d) {
		this.quantity = d;
	}

	public double getQuantity() {
		return quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	
	public boolean equals(Object obj) {
		return this.getName() == ((Item) obj).getName();
	}

	public void convertToItem(Product product){
		this.id=product.getId();
		this.name=product.getName();
		this.price=product.getPrice();
		this.quantity=1;

	}




}
