package io.pivotal.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.pivotal.web.domain.Order;
import io.pivotal.web.domain.Quote;
import io.pivotal.web.domain.Search;
import io.pivotal.web.service.MarketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TradeController {

	private static final Logger logger = LoggerFactory
			.getLogger(TradeController.class);
	
	@Autowired
	private MarketService marketService;
	
	@RequestMapping(value = "/trade", method = RequestMethod.GET)
	public String showTrade(Model model) {
		logger.debug("/trade.GET");
		//model.addAttribute("marketSummary", marketService.getMarketSummary());
		
		model.addAttribute("search", new Search());
		//check if user is logged in!
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug("User logged in: " + currentUserName);
		    
		    //TODO: add portfolio and account summary.
		}
		
		return "trade";
	}
	@RequestMapping(value = "/trade", method = RequestMethod.POST)
	public String showTrade(Model model, @ModelAttribute("search") Search search) {
		logger.debug("/trade.POST - symbol: " + search.getName());
		
		//model.addAttribute("marketSummary", marketService.getMarketSummary());
		model.addAttribute("search", search);
		
		if (search.getName() == null || search.getName().equals("") ) {
			model.addAttribute("quotes", new ArrayList<Quote>());
		} else {
			List<Quote> newQuotes = marketService.getQuotes(search.getName());
			model.addAttribute("quotes", newQuotes);
		}
		//check if user is logged in!
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug("User logged in: " + currentUserName);
		    model.addAttribute("order", new Order());
		    //TODO: add portfolio and account summary.
		}
		
		return "trade";
	}
	
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buy(Model model, @ModelAttribute("order") Order order) {
		
		model.addAttribute("search", new Search());
		
		// buy the order after setting attributes not set by the UI.
		//check if user is logged in!
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (!(authentication instanceof AnonymousAuthenticationToken)) {
				    String currentUserName = authentication.getName();
				    logger.debug("/buy ORDER: " + order);
				    order.setAccountId(currentUserName);
				    order.setCompletionDate(new Date());
				    
				    //TODO: change sendOrder to return Order and put that in model.
				    String result = marketService.sendOrder(order);
				} else {
					//should never get here!!!
				}
		return "trade";
	}
	
}