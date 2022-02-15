package br.com.iv.qikserve.service;

import org.springframework.stereotype.Service;

import br.com.iv.qikserve.helper.DoubleTools;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;

@Service
public class PromotionService {

	private final Integer FLAT_PERCENT = 10;
	private final Integer ONE_HUNDRED_PERCENT = 100;
//	private final String DECIMAL_FORMAT_2_DECIMAL_PLACES = "##0.00";
//	 
	/*
	 *  if qty is less the amount return productPirce * amount example: qtd 1 * price. 
	 *  otherwise  
	 *  calculate the logic
	 *  */
	public Double calculatorBuyXGetYFree(ProductModel product, PromotionModel promotion) {
		
		Double priceInDouble = DoubleTools.getValueWithSeparator(product.getPrice());
		
		if(product.getAmount() < promotion.getRequired_qty())
			return priceInDouble * product.getAmount();
		
		Integer qtyMatchedWithTheRule = product.getAmount() / promotion.getRequired_qty();
		Double valueMatchedWithTheRule = priceInDouble * qtyMatchedWithTheRule;
		
		Integer qtyDoesntMatchWithTheRulePromotion = product.getAmount() % promotion.getRequired_qty();
		Double valueDoesntMatchWithTheRule = priceInDouble * qtyDoesntMatchWithTheRulePromotion;

//		todo check which rule use.
//		Integer value = (product.getAmount() * product.getPrice()) - (product.getPrice() * promotion.getFree_qty());
		
		return valueMatchedWithTheRule + valueDoesntMatchWithTheRule;
		
	}

	public Double calculatorQtdBasedPriceOverride(ProductModel product, PromotionModel promotion) {

		Double priceInDouble = DoubleTools.getValueWithSeparator(product.getPrice());
		Double promotionPriceInDouble = DoubleTools.getValueWithSeparator(promotion.getPrice());
		
		if(product.getAmount() < promotion.getRequired_qty())
			return priceInDouble * product.getAmount();
		
		Integer qtyMatchedWithTheRule = product.getAmount() / promotion.getRequired_qty();
		Double valueMatchedWithTheRule = promotionPriceInDouble * qtyMatchedWithTheRule;
		
		Integer qtyDoesntMatchWithTheRulePromotion = product.getAmount() % promotion.getRequired_qty();
		Double valueDoesntMatchWithTheRule = priceInDouble * qtyDoesntMatchWithTheRulePromotion;
		
		return valueMatchedWithTheRule + valueDoesntMatchWithTheRule; 
		
	}

	public Double calculatorFlatPercent(ProductModel product, PromotionModel promotion) {
		
		Double amount = DoubleTools.getValueWithSeparator(product.getPrice() * product.getAmount());  
		
		if(product.getAmount() < promotion.getAmount())
			return amount;
		
		return amount - (amount * FLAT_PERCENT/ONE_HUNDRED_PERCENT);
		
	}
	
}
