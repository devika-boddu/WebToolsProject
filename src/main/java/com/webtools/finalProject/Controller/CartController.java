package com.webtools.finalProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CartController {
	@PostMapping("/cart.htm")
	public ModelAndView handleCost() {
		return new ModelAndView("user");
	}
}
