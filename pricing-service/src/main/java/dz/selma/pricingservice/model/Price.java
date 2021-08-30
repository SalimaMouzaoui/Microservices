package dz.selma.pricingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
	private Long productId;
	private Long priceId;
	private Integer originalPrice;
	private Integer discountedPrice;
}
