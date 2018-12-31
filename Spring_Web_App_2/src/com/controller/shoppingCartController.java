package com.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.implementation.OrderDAO;
import com.dao.implementation.ProductDAO;
import com.dao.layer.iOrder;
import com.pojo.Item;
import com.pojo.Order;
import com.pojo.Product;
import com.pojo.ShoppingCart;

@Controller
public class shoppingCartController {

	private static final Logger logger = Logger.getLogger(shoppingCartController.class);

	@RequestMapping(value="shoppingCart",method=RequestMethod.GET)
	public ModelAndView shoppingCartController(HttpSession session){

		ModelAndView mav = new ModelAndView("shoppingCart");
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		
		if (shoppingCart==null){
			shoppingCart = new ShoppingCart();
			logger.info("Shopping cart is null");
		}
		
		else{
		List<Item> items = shoppingCart.getItems();
		mav.addObject("items",items);
		System.out.print("Your shopping total is:"+ shoppingCart.gettotal());
		}
		
		return mav;

	}

	@RequestMapping(value="/addToShoppingCart/{id}", method=RequestMethod.GET)
	public String ShoppingController(@PathVariable("id")int id, Model model,HttpSession session){

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

		if (shoppingCart==null){
			shoppingCart = new ShoppingCart();
			logger.info("Shopping cart is null");
		}

		Item item = new Item();
		ProductDAO productDao = new ProductDAO();
		Product product = productDao.getProductById(id);
		item.convertToItem(product);	
		shoppingCart.addToCart(item);
		session.setAttribute("shoppingCart", shoppingCart);
		model.addAttribute("shoppingCart", shoppingCart);
		logger.info("Populated Cart with the id of:"+id);

		return "redirect:/productList";

	}


	@RequestMapping(value="cart/removeItem/{id}",method=RequestMethod.GET)
	public String viewProduct(@PathVariable("id")int id,HttpSession session){

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		shoppingCart.deleteFromCart(id);

		return "redirect:/shoppingCart";

	}

	@RequestMapping(value="cart/{id}/updateItem/{quantity}",method=RequestMethod.GET)
	public String editProduct(@PathVariable("id")int id,@PathVariable("quantity")int quantity,HttpSession session){

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		shoppingCart.editCart(id, quantity);
		logger.info("Id is:"+id+"quantity:"+quantity);

		return "redirect:/shoppingCart";

	}


	@RequestMapping(value="shoppingCart/checkOut",method=RequestMethod.GET)
	public ModelAndView checkOut(HttpSession session,Model model){

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<Item> items = shoppingCart.getItems();

		ModelAndView mav = new ModelAndView("checkOut");
		mav.addObject("items",items);

		Order order = new Order();
		//order.createOrder(shoppingCart);
		iOrder orderDao = new OrderDAO();
		orderDao.addOrder(order);
		logger.info("Order Saved with Id"+order.getId());


		return mav;

	}


}