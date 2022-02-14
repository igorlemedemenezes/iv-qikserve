package br.com.iv.qikserve.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductModel {

	@NotNull
	private String productId;
	
	@NotNull
	private Integer amount;
	
}
