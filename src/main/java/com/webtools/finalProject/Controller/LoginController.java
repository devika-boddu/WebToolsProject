package com.webtools.finalProject.Controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.finalProject.Dao.TravelPackagesDao;
import com.webtools.finalProject.Dao.UserDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Validator.LoginValidator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
public class LoginController {
	
	@Autowired
    private Environment environment;
	
	
	@Autowired
	LoginValidator loginValidator;

	@GetMapping("/login.htm")
	public ModelAndView handleLogin(ModelMap model, User user) {
		 model.addAttribute("user", user);
		return new ModelAndView("userLogin");
		
	}
	
	@PostMapping("/home.htm")
	public ModelAndView loginSuccessful(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("user") User user, BindingResult result) throws UserException, IOException {
		
		loginValidator.validate(user, result);
		
		if(result.hasErrors()) {
			return new ModelAndView("userLogin");
		}else {
		System.out.println("Entered home.htm");
		UserDao userDao = new UserDao();
		System.out.println(user.getName());
		User currentUser = userDao.getUser(user.getName());
//		String adminUsername = environment.getProperty("admin.username");
//		String adminPassword = environment.getProperty("admin.password");
//		String envrole = environment.getProperty("admin.role");
//		String role = request.getParameter("role");
//		System.out.println(role);
//		if(role.equals(envrole)) {
//			if(user.getName().equalsIgnoreCase(adminUsername)) {
//				if(user.getPassword().equals(adminPassword)) {
//					System.out.println("Admin Dashboard");
//					return new ModelAndView("adminDashboard");
//				}
//			}
//			
//		}else {
			System.out.println(currentUser);
			session.setAttribute("currentUser", currentUser);
			System.out.println(currentUser.getName());
			System.out.println(user.getName());
			System.out.println(currentUser.getPassword());
			System.out.println(user.getPassword());
			if(currentUser.getName().equalsIgnoreCase(user.getName())) {
				if(currentUser.getPassword().equals(user.getPassword())) {
					System.out.println("Valid Credentials");
				}else {
					System.out.println("Please check the Password");
					
				}
			}else {
				System.out.println("Username doesnot exist");
			}
//			ServletContext context = request.getServletContext();
//            String filePath = context.getRealPath("/static");
////			System.out.println(filePath + "HELLO");
//			
//			File imageFile = new File(filePath+"/Image.png");
//	        FileInputStream fis = new FileInputStream(imageFile);
//	        byte[] imageBytes = new byte[(int) imageFile.length()];
//	        fis.read(imageBytes);
//	        fis.close();
//
//	        // Set the response headers
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.IMAGE_JPEG);
//	        headers.setContentLength(imageBytes.length);
//	        System.out.println("I'm here");
	        
			TravelPackagesDao tdao = new TravelPackagesDao();
			List<TravelPackages> products =tdao.getProducts();
			session.setAttribute("products", products);
			for (TravelPackages i : products) {
				 
	            // Print all elements of ArrayList
				request.getSession().setAttribute("product", i);
	            System.out.println(i);
	        }
			
			return new ModelAndView("dashboard");
		}
	}
		


}
