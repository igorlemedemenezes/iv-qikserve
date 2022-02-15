package br.com.iv.qikserve.model;

import java.text.DecimalFormat;
import java.util.List;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ProductModel {

	public static void main(String[] args) {
		double valor = 200.3456;
		DecimalFormat df = new DecimalFormat("##0.00");
		String aux = df.format(valor).replace(",", ".");

		System.out.println(aux); // 200.35
	}
	
	private String id;
	private String name;
	private Integer price;
	private List<PromotionModel> promotions;
	
	@Min(1)
	private Integer amount;
	
	
}
