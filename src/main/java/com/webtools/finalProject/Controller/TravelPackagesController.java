package com.webtools.finalProject.Controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.finalProject.Dao.TravelPackagesDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class TravelPackagesController {
	
	String id = null;
	List<TravelPackages> cartItemsList = new ArrayList<TravelPackages>();
	List<TravelPackages> wishlistItemsList = new ArrayList<TravelPackages>();
	int totalCost = 0;
	
	
	@PostMapping("/products.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, BindingResult result) throws UserException {
		

		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		
		if(userSelectedOption.contains("Add To Cart")) {
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages addTocart=tdao.getSelectedProduct(tid);
			System.out.println(addTocart.getPackageName());
			System.out.println(addTocart.getPackageDescription());
			System.out.println(addTocart.getPackagePrice());
			totalCost += addTocart.getPackagePrice();
			cartItemsList.add(addTocart);
		}
		else {
			String pid = userSelectedOption.substring(16);
			Integer tid= Integer.parseInt(pid);
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages addToWishlist=tdao.getSelectedProduct(tid);
			System.out.println(addToWishlist.getPackageName());
			System.out.println(addToWishlist.getPackageDescription());
			System.out.println(addToWishlist.getPackagePrice());
			wishlistItemsList.add(addToWishlist);
		}
//		String selected = request.getParameter("myInput");
//		System.out.println(selected);
//		String pid = selected.substring(12);
//		System.out.println(pid);
//		Integer tid= Integer.parseInt(pid);
//		TravelPackagesDao tdao = new TravelPackagesDao();
//		TravelPackages addTocart=tdao.getSelectedProduct(tid);
//		System.out.println(addTocart.getPackageName());
//		System.out.println(addTocart.getPackageDescription());
//		System.out.println(addTocart.getPackagePrice());
//		cartItemsList.add(addTocart);
		session.setAttribute("cartItemsList", cartItemsList);
		session.setAttribute("wishlistItemsList", wishlistItemsList);
		session.setAttribute("totalCost", totalCost);
		for(TravelPackages i : cartItemsList) {
			System.out.println(i.getPackageName());
		}
		return new ModelAndView("dashboard");
		
	}
	
	
}
