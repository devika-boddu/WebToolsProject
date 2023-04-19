package com.webtools.finalProject.Controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.razorpay.RazorpayException;
import com.webtools.finalProject.Dao.TravelPackagesDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Pojo.Order;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class TravelPackagesController {
	
	String id = null;
	int count =0 ;
	List<TravelPackages> cartItemsList = new ArrayList<TravelPackages>();
	List<TravelPackages> wishlistItemsList = new ArrayList<TravelPackages>();
	int totalCost = 0;
	Order order = new Order();
	
	
	@PostMapping("/products.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, BindingResult result) throws UserException, RazorpayException {
		

		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		
		if(userSelectedOption.contains("Add To Cart")) {
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);
//			
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages addTocart=tdao.getSelectedProduct(tid);
			System.out.println(addTocart.getPackageName());
			System.out.println(addTocart.getPackageDescription());
			System.out.println(addTocart.getPackagePrice());
//			totalCost +=  addTocart.getPackagePrice();
			cartItemsList.add(addTocart);
		}
		else if(userSelectedOption.contains("Add To Wishlist")){
			String pid = userSelectedOption.substring(16);
			Integer tid= Integer.parseInt(pid);
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages addToWishlist=tdao.getSelectedProduct(tid);
			System.out.println(addToWishlist.getPackageName());
			System.out.println(addToWishlist.getPackageDescription());
			System.out.println(addToWishlist.getPackagePrice());
			//wishlistItemsList.add(addToWishlist);
			if(wishlistItemsList.size() > 0) {
				for(TravelPackages i : wishlistItemsList) {
					if(addToWishlist.getPackageId() == i.getPackageId()  ) {
						System.out.println("Item already exists!");
						break;
					}
					else {
						wishlistItemsList.add(addToWishlist);
						break;
					}
				}
			}else {
				wishlistItemsList.add(addToWishlist);
			}
		}
		else if(userSelectedOption.contains("Delete")) {
			String pid = userSelectedOption.substring(7);
			
			Integer tid= Integer.parseInt(pid);
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages removeItem = tdao.getSelectedProduct(tid);
			for(TravelPackages i : wishlistItemsList) {
				if(removeItem.getPackageId() == i.getPackageId()) {
					wishlistItemsList.remove(count);
					break;
				}	
				else {
					count+=1;
				}
			}
		
		
			
			//int ind = wishlistItemsList.indexOf(removeItem);
			//System.out.println("Index of item: " + ind);
			//wishlistItemsList.remove(ind);
			System.out.println(wishlistItemsList);			
			session.setAttribute("wishlistItemsList", wishlistItemsList);
		}
		else if(userSelectedOption.contains("Pay")) {
			order.createPayment();
			System.out.println("Orders");	
		}
		else if(userSelectedOption.contains("View")) {
			String pid = userSelectedOption.substring(5);
			Integer tid= Integer.parseInt(pid);
			System.out.println(tid);
			TravelPackagesDao tdao = new TravelPackagesDao();
			TravelPackages viewItem = tdao.getSelectedProduct(tid);
			session.setAttribute("viewItem", viewItem);
			return new ModelAndView("view");
		}  else if(userSelectedOption.contains("Quantity")){
			
			String quantity = request.getParameter("qtySelected");
			System.out.println(quantity);

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
//		session.setAttribute("totalCost", totalCost);
		for(TravelPackages i : wishlistItemsList) {
			System.out.println(i.getPackageId());
		}
		return new ModelAndView("dashboard");
		
	}
	
	
	
	
}
