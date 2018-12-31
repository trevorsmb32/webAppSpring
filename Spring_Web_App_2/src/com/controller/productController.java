package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.implementation.ProductDAO;
import com.pojo.Product;

@Controller
public class productController {
	
	private static final Logger logger = Logger.getLogger(productController.class);

	

	@RequestMapping(value="/productList",method=RequestMethod.GET)
	public String productList(Model model){

		ProductDAO produdctDAO = new ProductDAO();
		logger.info("productList page attempted GET");
		List<Product> products = produdctDAO.getAllProducts();
		model.addAttribute("products", products);
		
		return "productList";
	}
	
	
}

