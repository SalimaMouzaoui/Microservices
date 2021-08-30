package dz.selma.inventoryservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dz.selma.inventoryservice.model.Inventory;

@RestController
public class InventoryController {
	
	List<Inventory> inventoryList = new ArrayList<Inventory>();

	@GetMapping("/inventory/{productId}")
	public Inventory getInventoryDetails(@PathVariable Long productId) {
		// Get name and description from product service
		Inventory inventory = getInventoryInfo(productId);
		// Get price from pricing service
		
		// Get stock avail from inventory service
				
		return inventory;
	}

	private Inventory getInventoryInfo(Long productId) {
		populateInventoryList();
		for (Inventory i : inventoryList) {
			if (productId.equals(i.getProductId()))
					return i;
		}
		return null;
	}

	private void populateInventoryList() {
		inventoryList.add(new Inventory(6L, 1L , true));
		inventoryList.add(new Inventory(7L, 2L , true));
		inventoryList.add(new Inventory(8L, 3L , true));
		
	}
}
