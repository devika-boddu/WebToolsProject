package com.webtools.finalProject.Pojo;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order  {
	private double amount;
	private String currency;
	private int paymentId;
	

	public Order() {
	}
	
	public Order(double amount, String currency, int paymentId) {
		
		this.amount = amount;
		this.currency = currency;
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public void createPayment() throws RazorpayException   {
		
		
		RazorpayClient razorpay = new RazorpayClient("rzp_test_F4EfG17tUo4FRg", "rYKdtvKnJO2fsL3fXClBLuii");
		  //Order orderRequest = new Order();
		  JSONObject orderRequest = new JSONObject();
		  
		 // JSONObject options = new JSONObject();
		  orderRequest.put("amount", "50000"); // amount in the smallest currency unit
		  orderRequest.put("currency", "INR");
		  orderRequest.put("receipt", "order_rcptid_11");

		  com.razorpay.Order order;
		try {
			order = razorpay.orders.create(orderRequest);
			System.out.println(order);
		} catch (Exception e) {
			System.out.println(e);
		}
		  //System.out.println("Order details: " +order);
//		options.put("amount", 50000);
//		options.put("amount_paid", 0);
//		options.put("notes", "");
//		options.put("created_at", 1681837083);
//		options.put("amount_due", 50000);
//		options.put("currency", "INR");
//		options.put("receipt", "order_rcptid_11");
//		options.put("id", "order_LfKQDmZEon9Dbj");
//		options.put("entity", "order");
//		options.put("offer_id", "");
//		options.put("status", "created");
//		options.put("attempts", 0);
		
		//Map<String, Object> options = new HashMap<String, Object>();
		JSONObject options = new JSONObject();
        options.put("amount", "50000");
        options.put("currency", "INR");
//        options.put("name", "Acme Corp");
//        options.put("description", "Test Transaction");
//        options.put("image", "https://example.com/your_logo");
//        options.put("order_id", "order_IluGWxBm9U8zJ8");
//        options.put("prefill", new HashMap<String, Object>() {{
//            put("name", "Gaurav Kumar");
//            put("email", "gaurav.kumar@example.com");
//            put("contact", "9000090000");
//        }});
        options.put("notes", new HashMap<String, Object>() {{
            put("address", "Razorpay Corporate Office");
        }});
//        options.put("theme", new HashMap<String, Object>() {{
//            put("color", "#3399cc");
//        }});

        com.razorpay.Order paymentResponse = null;
		try {
			paymentResponse = razorpay.orders.create(options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        String paymentId = paymentResponse.getString("id");
//        String orderId = paymentResponse.getString("order_id");
//        String signature = paymentResponse.getString("razorpay_signature");
        
        String paymentId = paymentResponse.get("razorpay_payment_id");
        //String orderId = paymentResponse.getId();
        String signature = paymentResponse.get("razorpay_signature");


        System.out.println("Payment ID: " + paymentId);
        //System.out.println("Order ID: " + orderId);
       System.out.println("Signature: " + signature);
    
				
	}
	
}
