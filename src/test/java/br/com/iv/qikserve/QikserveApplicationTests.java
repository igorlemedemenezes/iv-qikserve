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
			
			Double value = promotionService.calculatorBuyXGetYFree(product, promotion.get());
			assertThat(value).isNotNull();
			assertEquals(value, 9.99);
			
			Double valueProduct1 = promotionService.calculatorBuyXGetYFree(product1, promotion.get());
			assertThat(valueProduct1).isNotNull();
			assertEquals(valueProduct1, 9.99);
			
			Double valueProduct2 = promotionService.calculatorBuyXGetYFree(product2, promotion.get());
			assertThat(valueProduct2).isNotNull();
			assertEquals(valueProduct2, 19.98);
			
			Double valueProduct3 = promotionService.calculatorBuyXGetYFree(product3, promotion.get());
			assertThat(valueProduct3).isNotNull();
			assertEquals(valueProduct3, 59.94);
			
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
			
			Double value = promotionService.calculatorQtdBasedPriceOverride(product, promotion.get());
			assertThat(value).isNotNull();
			assertEquals(value, 10.99);
			
			Double valueProduct1 = promotionService.calculatorQtdBasedPriceOverride(product1, promotion.get());
			assertThat(valueProduct1).isNotNull();
			assertEquals(valueProduct1, 17.99);
			
			Double valueProduct2 = promotionService.calculatorQtdBasedPriceOverride(product2, promotion.get());
			assertThat(valueProduct2).isNotNull();
			assertEquals(valueProduct2, 28.98);
			
			Double valueProduct3 = promotionService.calculatorQtdBasedPriceOverride(product3, promotion.get());
			assertThat(valueProduct3).isNotNull();
			assertEquals(valueProduct3, 100.94);
			
		}
		
	}
	
	@Test
	void callFunctionCalculatorFlatPercent_ShouldPass() {
		
		String idAmazingSalad = "C8GDyLrHJb";
		
		ProductModel product = wClient.getProductId(idAmazingSalad);
		product.setPrice(499);
		product.setAmount(1);
		
		ProductModel product1 = wClient.getProductId(idAmazingSalad);
		product1.setPrice(499);
		product1.setAmount(2);
		
		ProductModel product2 = wClient.getProductId(idAmazingSalad);
		product2.setPrice(499);
		product2.setAmount(10);

		ProductModel product3 = wClient.getProductId(idAmazingSalad);
		product3.setPrice(499);
		product3.setAmount(11);
		
		Optional<PromotionModel> promotion = product.getPromotions().stream().filter(e -> e.getId().equals(PromotionTypeEnum.FLAT_PERCENT.getCode())).findAny();
		
		if(promotion.isPresent()) {
			
			Double value = promotionService.calculatorFlatPercent(product, promotion.get());
			assertThat(value).isNotNull();
			assertEquals(value, 4.99);
			
			Double valueProduct1 = promotionService.calculatorFlatPercent(product1, promotion.get());
			assertThat(valueProduct1).isNotNull();
			assertEquals(valueProduct1, 9.98);
			
			Double valueProduct2 = promotionService.calculatorFlatPercent(product2, promotion.get());
			assertThat(valueProduct2).isNotNull();
			assertEquals(valueProduct2, 44.91);
			
			Double valueProduct3 = promotionService.calculatorFlatPercent(product3, promotion.get());
			assertThat(valueProduct3).isNotNull();
			assertEquals(valueProduct3, 49.40);
		}
		
	}
	

}
