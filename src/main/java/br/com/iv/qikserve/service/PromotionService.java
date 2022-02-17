package br.com.iv.qikserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.helper.DoubleTools;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;
import br.com.iv.qikserve.repository.ProductRepository;

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
		
		if(product.getProductQty() < promotion.getRequired_qty())
			return priceInDouble * product.getProductQty();
		
		Integer qtyMatchedWithTheRule = product.getProductQty() / promotion.getRequired_qty();
		Double valueMatchedWithTheRule = priceInDouble * qtyMatchedWithTheRule;
		
		Integer qtyDoesntMatchWithTheRulePromotion = product.getProductQty() % promotion.getRequired_qty();
		Double valueDoesntMatchWithTheRule = priceInDouble * qtyDoesntMatchWithTheRulePromotion;
		
		return valueMatchedWithTheRule + valueDoesntMatchWithTheRule;
		
	}

	public Double calculatorQtdBasedPriceOverride(ProductModel product, PromotionModel promotion) {

		Double priceInDouble = DoubleTools.getValueWithSeparator(product.getPrice());
		Double promotionPriceInDouble = DoubleTools.getValueWithSeparator(promotion.getPrice());
		
		if(product.getProductQty() < promotion.getRequired_qty())
			return priceInDouble * product.getProductQty();
		
		Integer qtyMatchedWithTheRule = product.getProductQty() / promotion.getRequired_qty();
		Double valueMatchedWithTheRule = promotionPriceInDouble * qtyMatchedWithTheRule;
		
		Integer qtyDoesntMatchWithTheRulePromotion = product.getProductQty() % promotion.getRequired_qty();
		Double valueDoesntMatchWithTheRule = priceInDouble * qtyDoesntMatchWithTheRulePromotion;
		
		return valueMatchedWithTheRule + valueDoesntMatchWithTheRule; 
		
	}

	public Double calculatorFlatPercent(ProductModel product, PromotionModel promotion) {
		
		Double amount = DoubleTools.getValueWithSeparator(product.getPrice() * product.getProductQty());  
		
		if(product.getProductQty() < promotion.getAmount())
			return amount;
		
		return amount - (amount * FLAT_PERCENT/ONE_HUNDRED_PERCENT);
	}
	
}
