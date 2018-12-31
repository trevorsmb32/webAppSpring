package com.pojo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="OrderHistory")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="orderId")
	int id;
	
	@Column(name="totalSpent")
	double ordertotal;

	@OneToOne(mappedBy="order",fetch=FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	ShoppingCart shopingCart = new ShoppingCart();
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn
	Customer customer;

	
	//@Temporal(TemporalType.DATE)
	//public Date dateTime;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void createOrder(ShoppingCart shopingCart,Customer customer) {
		this.shopingCart = shopingCart;
		this.ordertotal= shopingCart.gettotal();
		this.customer=customer;
	}

	

	/*	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	} */


	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + ordertotal + "]";
	}

}
