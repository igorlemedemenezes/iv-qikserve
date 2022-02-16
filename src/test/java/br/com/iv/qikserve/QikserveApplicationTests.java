package br.com.iv.qikserve;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.dto.BasketCheckoutDTO;
import br.com.iv.qikserve.enums.PromotionTypeEnum;
import br.com.iv.qikserve.helper.DoubleTools;
import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;
import br.com.iv.qikserve.service.BasketService;
import br.com.iv.qikserve.service.PromotionService;

@SpringBootTest
class QikserveApplicationTests {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private WiremockClient wClient;
	
	@Test
	void callFunctionCalculatorBuyXGetYFree_ShouldPass() {
		
		String idAmazingPizza = "Dwt5F7KAhi";
		
		ProductModel product = wClient.getProductId(idAmazingPizza);
		product.setPrice(1099);
		product.setAmount(1);
		
		ProductModel product1 = wClient.getProductId(idAmazingPizza);
		product1.setPrice(1099);
		product1.setAmount(2);

		
		ProductModel product2 = wClient.getProductId(idAmazingPizza);
		product2.setPrice(1099);
		product2.setAmount(3);


		ProductModel product3 = wClient.getProductId(idAmazingPizza);
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
			Double result = DoubleTools.decimalFormat("##0.00", valueProduct2);
			assertEquals(result, 28.98);
			
		}
		
	}

	@Test
	void callFunctionCalculatorQtdBasedPriceOverride_ShouldPass() {
		
		String idAmazingBurger = "PWWe3w1SDU";
		
		ProductModel product = wClient.getProductId(idAmazingBurger);
		product.setPrice(999);
		product.setAmount(1);
		
		ProductModel product1 = wClient.getProductId(idAmazingBurger);
		product1.setPrice(999);
		product1.setAmount(2);
		
		ProductModel product2 = wClient.getProductId(idAmazingBurger);
		product2.setPrice(999);
		product2.setAmount(3);

		ProductModel product3 = wClient.getProductId(idAmazingBurger);
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
			
		}
		
	}
	
	@Test
	void callFunctionCalculateTotal_ShouldPass() {
		
		String idAmazingSalad = "C8GDyLrHJb";
		String idAmazingPizza = "Dwt5F7KAhi";
		String idAmazingBurger = "PWWe3w1SDU";
		String idFries = "4MB7UfpTQs";
		List<ProductModel> products = new ArrayList<>();
		
		ProductModel pizza = wClient.getProductId(idAmazingPizza);
		pizza.setAmount(3);

		ProductModel burguer = wClient.getProductId(idAmazingBurger);
		burguer.setAmount(2);
		
		ProductModel salad = wClient.getProductId(idAmazingSalad);
		salad.setAmount(2);
		
		ProductModel fries = wClient.getProductId(idFries);
		fries.setAmount(4);

		products.add(pizza);
		products.add(burguer);
		products.add(salad);
		products.add(fries);
		
		BasketModel b = new BasketModel();
		b.setProducts(products);
		BasketCheckoutDTO total = basketService.calculateTotal(b);
		
		assertEquals(DoubleTools.decimalFormat("##0.00", total.getTotalPrice()), 56.91);
		assertThat(total).isNotNull();
	}

}
