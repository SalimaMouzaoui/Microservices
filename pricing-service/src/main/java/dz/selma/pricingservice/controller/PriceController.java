package dz.selma.pricingservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dz.selma.pricingservice.model.ExgVal;
import dz.selma.pricingservice.model.Price;

@RestController
public class PriceController {
	
	List<Price> priceList = new ArrayList<Price>();

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/price/{productId}")
	public Price getPriceDetails(@PathVariable Long productId) {
		// Get name and description from product service
		Price price = getPriceInfo(productId);
		// Get Exchange value from exchange service
		Integer exgVal = restTemplate.getForObject("http://localhost:8084/currexg/from/USD/to/YEN", 
				ExgVal.class).getExgVal();
						
		return new Price(price.getPriceId(), price.getProductId(),
				price.getOriginalPrice(), Math.multiplyExact(exgVal, price.getDiscountedPrice()));
	}

	private Price getPriceInfo(Long priceId) {
		populatePriceList();
		for (Price p : priceList) {
			if (priceId.equals(p.getProductId()))
					return p;
		}
		return null;
	}

	private void populatePriceList() {
		priceList.add(new Price(1L, 11L, 1999, 999));
		priceList.add(new Price(2L, 12L, 199, 20));
		priceList.add(new Price(3L, 13L, 500, 350));
		
	}
}
