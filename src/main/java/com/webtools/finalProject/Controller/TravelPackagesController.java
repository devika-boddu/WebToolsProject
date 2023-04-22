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
import com.webtools.finalProject.Dao.UserDao;
import com.webtools.finalProject.Dao.UserProductDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Pojo.UserProductMap;
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
	List<TravelPackages> searchedItems = new ArrayList<TravelPackages>();
	List<TravelPackages> sortedItems = new ArrayList<TravelPackages>();
	int totalCost = 0;
	int optionSelected = 0;
	Order order = new Order();
	
	
	@PostMapping("/products.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, @ModelAttribute("userproduct") UserProductMap userproduct, BindingResult result) throws UserException, RazorpayException {
		
		TravelPackagesDao tdao = new TravelPackagesDao();
		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		
		if(userSelectedOption.contains("Add To Cart")) {
//			UserDao userDao = new UserDao();
//			User currentUser = userDao.getUser(user.getName());
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);		
			
			TravelPackages addTocart=tdao.getSelectedProduct(tid);
//			userproduct.setTravelPackages(addTocart);
//			userproduct.setUser(user);
			UserProductDao updao = new UserProductDao();
			User user = (User) session.getAttribute("currentUser");
			System.out.println("Logged In User "+ user);
			System.out.println("Product Added To Cart"+ addTocart);
			UserProductMap upmap = updao.create(new UserProductMap(user, addTocart));
			System.out.println(upmap);
			System.out.println(addTocart.getPackageName());
			System.out.println(addTocart.getPackageDescription());
			System.out.println(addTocart.getPackagePrice());
//			totalCost +=  addTocart.getPackagePrice();
			cartItemsList.add(addTocart);
		}
		else if(userSelectedOption.contains("Add To Wishlist")){
			String pid = userSelectedOption.substring(16);
			Integer tid= Integer.parseInt(pid);
			
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
			
			TravelPackages viewItem = tdao.getSelectedProduct(tid);
			session.setAttribute("viewItem", viewItem);
			return new ModelAndView("view");
		}  else if (userSelectedOption.contains("Search")) {
			String enteredText = request.getParameter("textEntered");
			System.out.println(enteredText);
			searchedItems=tdao.getSearchedProducts(enteredText);
			for(TravelPackages i : searchedItems) {
				System.out.println(i.getPackageId());
			}
			optionSelected=1;
		}else if (userSelectedOption.contains("Sort")) {
			sortedItems=tdao.getSortedProducts();
			optionSelected=2;
		}else if(userSelectedOption.contains("Quantity")){
				totalCost = 0;
				String[] selectedValues = request.getParameterValues("qty");
				System.out.println(selectedValues);
				for (int i = 0; i < cartItemsList.size(); i++) {
				TravelPackages item = cartItemsList.get(i);
				totalCost += item.getPackagePrice() * Integer.parseInt(selectedValues[i]);
				}
				System.out.println(totalCost);
			}

		else if(userSelectedOption.matches(".*\\d+.*")){
			
			Integer pageNumber = Integer.parseInt(userSelectedOption);
			tdao.getPaginationResults(pageNumber);
		}
		
		session.setAttribute("sortedItems", sortedItems);
		session.setAttribute("optionSelected", optionSelected);
		session.setAttribute("searchedItems", searchedItems);
		session.setAttribute("cartItemsList", cartItemsList);
		session.setAttribute("wishlistItemsList", wishlistItemsList);
		session.setAttribute("totalCost", totalCost);
		for(TravelPackages i : wishlistItemsList) {
			System.out.println(i.getPackageId());
		}
		return new ModelAndView("dashboard");
		
	}
	
	
	
	
}
