package br.com.iv.qikserve.model;

import lombok.Data;

@Data
public class PromotionModel {

	private String id;
	private String type;
	private Integer required_qty;
	private Integer price;
	private Integer amount;
	private Integer free_qty;
	
}
