package br.com.iv.qikserve.model;

import java.util.List;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ProductModel {

	private String id;
	private String name;
	private Integer price;
	private List<PromotionModel> promotions;
	
	@Min(1)
	private Integer amount;
	
	
}
