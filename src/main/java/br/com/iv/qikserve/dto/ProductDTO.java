package br.com.iv.qikserve.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDTO {

	@NotNull
	private String productId;
	
	@Min(1)
	@NotNull
	private Integer amount;
	
}
