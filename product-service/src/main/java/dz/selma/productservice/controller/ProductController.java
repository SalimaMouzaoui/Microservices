package dz.selma.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dz.selma.productservice.model.Inventory;
import dz.selma.productservice.model.Price;
import dz.selma.productservice.model.Product;
import dz.selma.productservice.model.ProductInfo;

@RestController
public class ProductController {
	
	List<ProductInfo> productList = new ArrayList<ProductInfo>();

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/product/details/{productId}")
	public Product getProductDetails(@PathVariable Long productId) {
		// Get name and description from product service
		ProductInfo productInfo = getProductInfo(productId);
		// Get price from pricing service
		Price price = restTemplate.getForObject("http://localhost:8082/price/"+productId, Price.class);
		// Get stock avail from inventory service
		Inventory inventory = restTemplate.getForObject("http://localhost:8083/inventory/"+productId, Inventory.class);
		
		return new Product(productInfo.getProductId(), 
				productInfo.getProductName(), 
				productInfo.getProductDesc(), price.getDiscountedPrice(), inventory.getInStock());
	}

	private ProductInfo getProductInfo(Long productId) {
		populateProductList();
		for (ProductInfo productInfo : productList) {
			if (productId.equals(productInfo.getProductId()))
					return productInfo;
		}
		return null;
	}

	private void populateProductList() {
		productList.add(new ProductInfo(1L, "Iphone", "Iphone is damn expensive !!"));
		productList.add(new ProductInfo(2L, "Book", "Book is great !!"));
		productList.add(new ProductInfo(3L, "Washing wc", "Washing wc is crucial !!"));
		
	}
}
