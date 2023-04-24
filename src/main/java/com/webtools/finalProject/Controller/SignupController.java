  package com.webtools.finalProject.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.User;

import com.webtools.finalProject.Validator.SignupValidator;
import com.webtools.finalProject.Dao.UserDao;



@Controller
public class SignupController {
	
	@Autowired
	SignupValidator signupValidator;
	
	
	@GetMapping("/signup.htm")
	public ModelAndView handleLogin(ModelMap model, User user) {
		 model.addAttribute("user", user);
		return new ModelAndView("signup");
		
	}
	
	@PostMapping("/signUpLogin.htm")
	public String handleUserSignUp(UserDao userDao,  HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("user") User user, BindingResult result) throws Exception
	{
		signupValidator.validate(user, result);
		
		if(result.hasErrors()) {
			return "signup";
		}else {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
//			String streetName = request.getParameter("streetName");
//			int streetNum = Integer.parseInt(request.getParameter("streetNum"));
//			String city = request.getParameter("city");
//			String state = request.getParameter("state");
//			String zipcode = request.getParameter("zipcode");
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(password);
			
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			
//			Address address = new Address();
//			address.setStreetName(streetName);
//			address.setStreetNum(streetNum);
//			address.setCity(city);
//			address.setState(state);
//			address.setZip(zipcode);
//			AddressDao addressDao = new AddressDao();
			
			try {
				userDao.save(user);
				//addressDao.save(address);
				session.setAttribute("user", user);
//				session.setAttribute("address", address);
				
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "userLogin";
			
		}
	
	}

}
