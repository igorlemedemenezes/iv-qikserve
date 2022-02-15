package br.com.iv.qikserve;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.enums.PromotionTypeEnum;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;
import br.com.iv.qikserve.service.PromotionService;

@SpringBootTest
class QikserveApplicationTests {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private WiremockClient wClient;
	
	@Test
	void callFunctionCalculatorBuyXGetYFree_ShouldPass() {
		
		String idAmazingPizza = "PWWe3w1SDU";
		
		ProductModel product = wClient.getProductId(idAmazingPizza);
		product.setPrice(999);
		product.setAmount(1);
		
		ProductModel product1 = wClient.getProductId(idAmazingPizza);
		product1.setPrice(999);
		product1.setAmount(2);
		
		ProductModel product2 = wClient.getProductId(idAmazingPizza);
		product2.setPrice(999);
		product2.setAmount(3);

		ProductModel product3 = wClient.getProductId(idAmazingPizza);
		product3.setPrice(999);
		product3.setAmount(11);
		
		Optional<PromotionModel> promotion = product.getPromotions().stream().filter(e -> e.getId().equals(PromotionTypeEnum.BUY_X_GET_Y_FREE.getCode())).findAny();
		
		if(promotion.isPresent()) {
			
			Integer value = promotionService.calculatorBuyXGetYFree(product, promotion.get());
			assertThat(value).isNotNull();
			assertEquals(value, 999);
			
			Integer valueProduct1 = promotionService.calculatorBuyXGetYFree(product1, promotion.get());
			assertThat(valueProduct1).isNotNull();
			assertEquals(valueProduct1, 999);
			
			Integer valueProduct2 = promotionService.calculatorBuyXGetYFree(product2, promotion.get());
			assertThat(valueProduct2).isNotNull();
			assertEquals(valueProduct2, 1998);
			
			Integer valueProduct3 = promotionService.calculatorBuyXGetYFree(product3, promotion.get());
			assertThat(valueProduct3).isNotNull();
			assertEquals(valueProduct3, 5994);
			
		}
		
	}
	
	@Test
	void callFunctionCalculatorQtdBasedPriceOverride_ShouldPass() {
		
		String idAmazingBurger = "PWWe3w1SDU";
		
		ProductModel product = wClient.getProductId(idAmazingBurger);
		product.setPrice(1099);
		product.setAmount(1);
		
		ProductModel product1 = wClient.getProductId(idAmazingBurger);
		product1.setPrice(1099);
		product1.setAmount(2);
		
		ProductModel product2 = wClient.getProductId(idAmazingBurger);
		product2.setPrice(1099);
		product2.setAmount(3);

		ProductModel product3 = wClient.getProductId(idAmazingBurger);
		product3.setPrice(1099);
		product3.setAmount(11);
		
		Optional<PromotionModel> promotion = product.getPromotions().stream().filter(e -> e.getId().equals(PromotionTypeEnum.QTY_BASED_PRICE_OVERRIDE.getCode())).findAny();
		
		if(promotion.isPresent()) {
			
			Integer value = promotionService.calculatorQtdBasedPriceOverride(product, promotion.get());
			assertThat(value).isNotNull();
			assertEquals(value, 1099);
			
			Integer valueProduct1 = promotionService.calculatorQtdBasedPriceOverride(product1, promotion.get());
			assertThat(valueProduct1).isNotNull();
			assertEquals(valueProduct1, 1799);
			
			Integer valueProduct2 = promotionService.calculatorQtdBasedPriceOverride(product2, promotion.get());
			assertThat(valueProduct2).isNotNull();
			assertEquals(valueProduct2, 2898);
			
			Integer valueProduct3 = promotionService.calculatorQtdBasedPriceOverride(product3, promotion.get());
			assertThat(valueProduct3).isNotNull();
			assertEquals(valueProduct3, 10094);
			
		}
		
	}
	

}
