package com.webtools.finalProject.Controller;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
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
import com.webtools.finalProject.Dao.UserProductDao;
import com.webtools.finalProject.Dao.UserOrderDao;
import com.webtools.finalProject.Dao.UserWishlistDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Pojo.UserProductMap;
import com.webtools.finalProject.Pojo.UserWishlistMap;
import com.webtools.finalProject.Pojo.UserOrderMap;
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
		List<UserProductMap> userProducts = null;
		List<UserOrderMap> userOrders = null;
		List<UserWishlistMap> userWishlistItems = null;
		List<TravelPackages> travelPackagesCart = new ArrayList<TravelPackages>();
		List<TravelPackages> travelPackagesOrders = new ArrayList<TravelPackages>();
		List<TravelPackages> travelPackagesWishlist = new ArrayList<TravelPackages>();
		if(result.hasErrors()) {
			return new ModelAndView("userLogin");
		}else {
		System.out.println("Entered home.htm");
		UserDao userDao = new UserDao();
		System.out.println(user.getName());
		User currentUser = userDao.getUser(user.getName());
			System.out.println(currentUser);
			session.setAttribute("currentUser", currentUser);
			System.out.println(currentUser.getName());
			System.out.println(user.getName());
			System.out.println(currentUser.getPassword());
			System.out.println(user.getPassword());
			if(currentUser.getName().equalsIgnoreCase(user.getName())) {
				if(currentUser.getPassword().equals(user.getPassword())) {
					System.out.println("Valid Credentials");
					
					UserProductDao userProdDao = new UserProductDao();
					UserOrderDao userOrderDao = new UserOrderDao();
					UserWishlistDao userWishlistDao = new UserWishlistDao();
					TravelPackagesDao tpdao = new TravelPackagesDao();
					userProducts = userProdDao.getAllUserProducts(currentUser);
					userOrders = userOrderDao.getAllUserOrders(currentUser);
					userWishlistItems = userWishlistDao.getAllUserWishlistItems(currentUser);
					for(UserProductMap i : userProducts) {
					 System.out.println("userProducts 1: "+ i.getTravelPackages().getPackageId());
					 travelPackagesCart.add(tpdao.getSelectedProduct(i.getTravelPackages().getPackageId()));
					 System.out.println("travelPackagesCart------"+travelPackagesCart);
					
					}
					for(UserOrderMap i : userOrders) {
						 System.out.println("userOrders 1: "+ i.getTravelPackages().getPackageId());
						 travelPackagesOrders.add(tpdao.getSelectedProduct(i.getTravelPackages().getPackageId()));
						 System.out.println("travelPackagesOrders -----"+travelPackagesOrders);
						
					}
					for(UserWishlistMap i : userWishlistItems) {
						 System.out.println("userWishlistItems 1: "+ i.getTravelPackages().getPackageId());
						 travelPackagesWishlist.add(tpdao.getSelectedProduct(i.getTravelPackages().getPackageId()));
						 System.out.println("travelPackagesOrders -----"+travelPackagesWishlist);
						
					}
					
				}else {
					System.out.println("Please check the Password");
					
				}
			}else {
				System.out.println("Username doesnot exist");
			}
	       
			TravelPackagesDao tdao = new TravelPackagesDao();
			List<TravelPackages> products =tdao.getProducts();
			session.setAttribute("products", products);
			session.setAttribute("travelPackagesCart", travelPackagesCart);
			session.setAttribute("travelPackagesOrders", travelPackagesOrders);
			session.setAttribute("travelPackagesWishlist", travelPackagesWishlist);
			for (TravelPackages i : products) {
				
	            // Print all elements of ArrayList
				request.getSession().setAttribute("product", i);
	            System.out.println(i);
	        }
			
			return new ModelAndView("dashboard");
		}
	}
		
}