package dz.selma.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfo {

	private Long productId;
	private String productName;
	private String productDesc;
	
}
