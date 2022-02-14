package br.com.iv.qikserve.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BasketModel {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer userId;
	
	private List<ProductModel> products = new ArrayList<>();
	
	
	
}
