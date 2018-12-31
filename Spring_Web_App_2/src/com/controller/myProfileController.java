package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.implementation.CustomerDao;
import com.doa.registery.RegisteryDAO;
import com.pojo.Customer;
import com.pojo.Product;
import com.pojo.ShippingAddress;

@Controller
public class myProfileController {

	private static final Logger logger = Logger.getLogger(adminController.class);
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
			
	}
	
	@RequestMapping(value="myProfile",method=RequestMethod.GET)
	public ModelAndView profileLogin(HttpSession session, Model mod){
		
		ModelAndView mav = new ModelAndView("myProfile");
		String username = session.getAttribute("username").toString();
		mod.addAttribute("user",username);
		
		return mav;
			
	}
	

	@RequestMapping(value="register",method=RequestMethod.GET)
	public ModelAndView registerView(){
		
		ModelAndView mav = new ModelAndView("register");
		Customer customer = new Customer();
		ShippingAddress sa = new ShippingAddress();
		customer.setShippingAddress(sa);
		mav.addObject("customer", customer);
		
		return mav;
		
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registerCustomer(@Valid@ModelAttribute("customer") Customer customer, BindingResult br){
		
			logger.info("binding result"+ br);
			CustomerDao dao = new CustomerDao();
			logger.info("Attempting to add customer");
			dao.addCustomer(customer);
						
			
			return "redirect:/productList";
			
	}
	
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }
    
    @RequestMapping(value = "/login_test", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }
    
    @RequestMapping(value = "/login_test2", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("login");
		model.addObject("msg", "hello world");
		
		return model;
	}
    
}
