package br.com.iv.qikserve.model;

import lombok.Data;

@Data
public class PromotionModel {

	private String id;
	private String type;
	private Integer required_qty;
	private Double price;
	
}
