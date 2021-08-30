package dz.selma.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	private Long productId;
	private String productName;
	private String productDesc;
	private Integer productPrice;
	private Boolean productStock;
}
