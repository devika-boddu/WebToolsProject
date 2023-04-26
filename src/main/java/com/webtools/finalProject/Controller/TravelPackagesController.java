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
import com.webtools.finalProject.Dao.UserOrderDao;
import com.webtools.finalProject.Dao.UserWishlistDao;
import com.webtools.finalProject.Exception.UserException;
import com.webtools.finalProject.Pojo.TravelPackages;
import com.webtools.finalProject.Pojo.User;
import com.webtools.finalProject.Pojo.UserOrderMap;
import com.webtools.finalProject.Pojo.UserProductMap;
import com.webtools.finalProject.Pojo.UserWishlistMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class TravelPackagesController {
	
	String id = null;
	int count =0 ;
	int updateValue =0;
	List<TravelPackages> cartItemsList = new ArrayList<TravelPackages>();
	List<TravelPackages> wishlistItemsList = new ArrayList<TravelPackages>();
	List<TravelPackages> searchedItems = new ArrayList<TravelPackages>();
	List<TravelPackages> sortedItems = new ArrayList<TravelPackages>();
	List<TravelPackages> ordersList = new ArrayList<TravelPackages>();
	List<TravelPackages> cartList = new ArrayList<TravelPackages>();
	List<TravelPackages> wishList = new ArrayList<TravelPackages>();
	List<TravelPackages> orderCartList = new ArrayList<TravelPackages>();
	List<TravelPackages> paginationResults = new ArrayList<TravelPackages>();
	List<TravelPackages> previousOrderList = new ArrayList<TravelPackages>();

	
	int totalCost = 0;
	int aTotalCost = 0;
	int optionSelected = 0;
	TravelPackagesDao tdao = new TravelPackagesDao();

	
//	@GetMapping("/wishlist.htm")
//	public String getSearchedProducts(HttpServletRequest request, HttpServletResponse response, HttpSession session,
//			@ModelAttribute("travelPackage") TravelPackages travelPackage, @ModelAttribute("userproduct") UserProductMap userproduct, BindingResult result) throws UserException {
//		int wishCount =0;
//		String userSelectedOption = request.getParameter("userSelectedOption");
//		wishList = (List<TravelPackages>) session.getAttribute("travelPackagesWishlist");
//		String pid = userSelectedOption.substring(16);
//		Integer tid= Integer.parseInt(pid);
//		for(TravelPackages wish : wishList) {
//			System.out.println(wish.getPackageId());
//		}
//		
//		TravelPackages addToWishlist=tdao.getSelectedProduct(tid);
//		
//		UserWishlistDao uwdao = new UserWishlistDao();
//		User user = (User) session.getAttribute("currentUser");
//		System.out.println("Logged In User for Wishlist:"+ user);
//		System.out.println("Product added to wishlist" + addToWishlist);
//		
//		
//		System.out.println(addToWishlist.getPackageName());
//		System.out.println(addToWishlist.getPackageDescription());
//		System.out.println(addToWishlist.getPackagePrice());
//		//wishlistItemsList.add(addToWishlist);
//		if(wishList.size() > 0) {
//			for(TravelPackages i : wishList) {
//				if(addToWishlist.getPackageId() == i.getPackageId()  ) {
//					System.out.println("Item already exists!");
//					wishCount += 1;
//				}
//			}if(wishCount == 0) {
//				wishList.add(addToWishlist);
//				UserWishlistMap uwmap = uwdao.create(new UserWishlistMap(user, addToWishlist));
//			}else {
//				System.out.println("Item exists");
//			}
//	
//		}else {
//			wishList.add(addToWishlist);
//			UserWishlistMap uwmap = uwdao.create(new UserWishlistMap(user, addToWishlist));
//		}
//		session.setAttribute("travelPackagesWishlist", wishList);
//
//		
//		return "dashboard";
//	}

	
	@PostMapping("/products.htm")
	public ModelAndView handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, @ModelAttribute("userproduct") UserProductMap userproduct, BindingResult result) throws UserException, RazorpayException {
		
				String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		
		if(userSelectedOption.contains("Add To Cart")) {
			cartList = (List<TravelPackages>) session.getAttribute("travelPackagesCart");
			int cartCount =0;
//			UserDao userDao = new UserDao();
//			User currentUser = userDao.getUser(user.getName());
			String pid = userSelectedOption.substring(12);
			Integer tid= Integer.parseInt(pid);		
			
			TravelPackages addTocart=tdao.getSelectedProduct(tid);
//			userproduct.setTravelPackages(addTocart);
//			userproduct.setUser(user);
			UserProductDao updao = new UserProductDao();
			User user = (User) session.getAttribute("currentUser");
			System.out.println("Logged In User for Cart "+ user);
			System.out.println("Product Added To Cart"+ addTocart);
			
			System.out.println(addTocart.getPackageName());
			System.out.println(addTocart.getPackageDescription());
			System.out.println(addTocart.getPackagePrice());
			
			if(cartList.size() > 0) {
				for(TravelPackages i : cartList) {
					if(addTocart.getPackageId() == i.getPackageId()  ) {
						System.out.println("Item already exists!");
						cartCount += 1;
					}
				}if(cartCount == 0) {
					cartList.add(addTocart);
					UserProductMap upmap = updao.create(new UserProductMap(user, addTocart));
				}else {
					System.out.println("Item exists");
				}
		
			}else {
				cartList.add(addTocart);
				UserProductMap upmap = updao.create(new UserProductMap(user, addTocart));
			}
			
			session.setAttribute("travelPackagesCart", cartList);
			
		}
		else if(userSelectedOption.contains("Add To Wishlist")){
			int wishCount =0;
			wishList = (List<TravelPackages>) session.getAttribute("travelPackagesWishlist");
			String pid = userSelectedOption.substring(16);
			Integer tid= Integer.parseInt(pid);
			for(TravelPackages wish : wishList) {
				System.out.println(wish.getPackageId());
			}
			
			TravelPackages addToWishlist=tdao.getSelectedProduct(tid);
			
			UserWishlistDao uwdao = new UserWishlistDao();
			User user = (User) session.getAttribute("currentUser");
			System.out.println("Logged In User for Wishlist:"+ user);
			System.out.println("Product added to wishlist" + addToWishlist);
			
			
			System.out.println(addToWishlist.getPackageName());
			System.out.println(addToWishlist.getPackageDescription());
			System.out.println(addToWishlist.getPackagePrice());
			//wishlistItemsList.add(addToWishlist);
			if(wishList.size() > 0) {
				for(TravelPackages i : wishList) {
					if(addToWishlist.getPackageId() == i.getPackageId()  ) {
						System.out.println("Item already exists!");
						wishCount += 1;
					}
				}if(wishCount == 0) {
					wishList.add(addToWishlist);
					UserWishlistMap uwmap = uwdao.create(new UserWishlistMap(user, addToWishlist));
				}else {
					System.out.println("Item exists");
				}
		
			}else {
				wishList.add(addToWishlist);
				UserWishlistMap uwmap = uwdao.create(new UserWishlistMap(user, addToWishlist));
			}
			session.setAttribute("travelPackagesWishlist", wishList);

		}
		else if(userSelectedOption.contains("Delete")) {
			wishList = (List<TravelPackages>) session.getAttribute("travelPackagesWishlist");
			UserWishlistDao uwdao = new UserWishlistDao();
			String pid = userSelectedOption.substring(7);
			
			Integer tid= Integer.parseInt(pid);
			System.out.println("Delete Id: "+tid);
			TravelPackages removeItem = tdao.getSelectedProduct(tid);
			for(TravelPackages i : wishList) {
				if(removeItem.getPackageId() == i.getPackageId()) {
					wishList.remove(count);
					uwdao.deleteSelectedWishlistItem(i);
					break;
				}	
				else {
					count+=1;
				}
			}

			System.out.println(wishList);			
			session.setAttribute("travelPackagesWishlist", wishList);
		}
		else if(userSelectedOption.contains("Pay")) {
			previousOrderList = (List<TravelPackages>) session.getAttribute("travelPackagesOrders");
			orderCartList = (List<TravelPackages>) session.getAttribute("travelPackagesCart");
			User user = (User) session.getAttribute("currentUser");
			UserOrderDao uodao = new UserOrderDao();
			for(TravelPackages order: orderCartList) {
				ordersList.add(order);
				//aTotalCost+=order.getPackagePrice();
				uodao.create(new UserOrderMap(user, tdao.getSelectedProduct(order.getPackageId()),tdao.getSelectedProduct(order.getPackageId()).getPackagePrice()));
			}
			for(TravelPackages order: previousOrderList) {
				ordersList.add(order);
			}
			cartList.clear();
			UserProductDao updao= new UserProductDao();
			updao.deleteAllTravelPackages();
			session.setAttribute("travelPackagesCart", cartList);
			System.out.println(ordersList);
			System.out.println("Orders");
			session.setAttribute("travelPackagesOrders", ordersList);
			//System.out.println(aTotalCost);					
		}
//		else if(userSelectedOption.contains("View")) {
//			String pid = userSelectedOption.substring(5);
//			Integer tid= Integer.parseInt(pid);
//			System.out.println(tid);
//			
//			TravelPackages viewItem = tdao.getSelectedProduct(tid);
//			session.setAttribute("viewItem", viewItem);
//			return new ModelAndView("view");
//	}  
		else if (userSelectedOption.contains("Search")) {
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
		}else if(userSelectedOption.contains("Total")){
			orderCartList = (List<TravelPackages>) session.getAttribute("travelPackagesCart");
			for(TravelPackages order: orderCartList) {
				ordersList.add(order);
				aTotalCost+=order.getPackagePrice();
			}
			System.out.println(aTotalCost);					
			
//				totalCost = 0;
//				String[] selectedValues = request.getParameterValues("qty");
//				System.out.println(selectedValues);
//				
//				for (int i = 0; i < cartItemsList.size(); i++) {
//				TravelPackages item = cartItemsList.get(i);
//				totalCost += item.getPackagePrice() * Integer.parseInt(selectedValues[i]);
//				}
//				System.out.println(totalCost);
			}

		else if(userSelectedOption.matches(".*\\d+.*")){
			
			Integer pageNumber = Integer.parseInt(userSelectedOption);
			paginationResults = tdao.getPaginationResults(pageNumber);
			optionSelected=3;
		}
		else if(userSelectedOption.contains("Update")) {
			UserDao userdao = new UserDao();
			User user = (User) session.getAttribute("currentUser");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			userdao.updateUser(user.getId(),username, email );
			updateValue =1;
		}
		session.setAttribute("sortedItems", sortedItems);
		session.setAttribute("optionSelected", optionSelected);
		session.setAttribute("searchedItems", searchedItems);
		session.setAttribute("aTotalCost", aTotalCost);
		session.setAttribute("paginationResults", paginationResults);
		session.setAttribute("updateValue", updateValue);
				System.out.println();
		for(TravelPackages i : wishlistItemsList) {
			System.out.println(i.getPackageId());
		}
		return new ModelAndView("dashboard1");
		
	}

	@GetMapping("/view.htm")
	public ModelAndView handleView(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, @ModelAttribute("userproduct") UserProductMap userproduct, BindingResult result) throws UserException{
		String userSelectedOption = request.getParameter("userSelectedOption");
		System.out.println(userSelectedOption);
		if(userSelectedOption.contains("View")) {
			String pid = userSelectedOption.substring(5);
			Integer tid= Integer.parseInt(pid);
			System.out.println("tid: "+tid);
			
			TravelPackages viewItem = tdao.getSelectedProduct(tid);
			session.setAttribute("viewItem", viewItem);	
		
	}
		return new ModelAndView("view");
	
	
	}
	
	@GetMapping("/back.htm")
	public ModelAndView handleBack(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("travelPackage") TravelPackages travelPackage, @ModelAttribute("userproduct") UserProductMap userproduct, BindingResult result) throws UserException{
		return new ModelAndView("dashboard1");
	
	}
	}	

