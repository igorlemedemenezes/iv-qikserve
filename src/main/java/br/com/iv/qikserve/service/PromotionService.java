package br.com.iv.qikserve.service;

import org.springframework.stereotype.Service;

import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;

@Service
public class PromotionService {

	public Integer calculatorBuyXGetYFree(ProductModel product, PromotionModel promotion) {
		
		if(product.getAmount() < promotion.getRequired_qty())
			return product.getPrice() * promotion.getAmount();

		return (product.getPrice() * product.getAmount()) - product.getPrice();
		
	}

	public Integer calculatorQtdBasedPriceOverride(ProductModel product, PromotionModel promotion) {
		if(product.getAmount() == promotion.getRequired_qty())
			return product.getPrice() * promotion.getAmount();
		return null;
//		todo logic to promotion
		
	}

	public Integer calculatorFlatPercent(ProductModel product, PromotionModel promotion) {
		
		if(product.getAmount() < promotion.getRequired_qty())
			return product.getPrice() * promotion.getAmount();
		
		return null;
//		todo logic to promotion
		
	}

	
	
}
