package br.com.iv.qikserve.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.dto.BasketDTO;
import br.com.iv.qikserve.dto.BasketTotalPayableDTO;
import br.com.iv.qikserve.dto.PriceOrderDetailsDTO;
import br.com.iv.qikserve.enums.PromotionTypeEnum;
import br.com.iv.qikserve.helper.DoubleTools;
import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;

@Service
public class BasketService {

	private final String DECIMAL_FORMAT = "##0.00"; 
	
//	When add item, need check how much have at the itens.
	
	@Autowired
	private WiremockClient wiremockClient;
	
	@Autowired
	private PromotionService promotionService;
	
	public BasketModel create(BasketModel basket) {
		basket.getProducts().stream().map(product -> {
			return wiremockClient.getProductId(product.getId());
		}).collect(Collectors.toList());
//		todo save
		return basket;
	}

	public BasketModel addItem(BasketDTO basketDTO) {
		BasketModel basket = findById(basketDTO.getBasketId());
		ProductModel product = wiremockClient.getProductId(basketDTO.getProduct().getId());
		addNewProductToBasket(basketDTO, basket, product);
		return basket;
	}

	private void addNewProductToBasket(BasketDTO basketDTO, BasketModel basket, ProductModel productRetrieved) {
		Optional<ProductModel> product = null;
		
		if(basket.getProducts() != null) 
			product = basket.getProducts().stream().filter(e -> e.getId().equals(basketDTO.getProduct().getId())).findAny();
		
		if(product != null && product.isPresent()) {
			Integer totalAmount = product.get().getAmount()+basketDTO.getProduct().getAmount();
			product.get().setAmount(totalAmount);
		}
		else {
			productRetrieved.setAmount(basketDTO.getProduct().getAmount());
			basket.getProducts().add(productRetrieved);
			
		}
			
	}

	private BasketModel findById(Integer id) {
//		Optional<BasketModel> = repo.findById(id);
		return new BasketModel();
	}

	public BasketTotalPayableDTO checkoutBasketWithTotalPayable(Integer id) {
		BasketModel basket = findById(id);
		return calculateTotalPayable(basket);
	}
	
	public List<String> getBasketContents(BasketModel basket){
		
		List<String> basketContents = new ArrayList<String>();
		
		basket.getProducts().forEach(e -> {
			basketContents.add(e.getAmount() + "x" + e.getName());
		});
		
		return basketContents;

	}
	
	public Double calculateTotal(BasketModel basket) {
		
		return  DoubleTools.decimalFormat(DECIMAL_FORMAT, 
				basket.getProducts()
					.stream()
					.mapToDouble(product -> (product.getAmount() * DoubleTools.getValueWithSeparator(product.getPrice())))
					.sum());
	}
	
	public BasketTotalPayableDTO calculateTotalPayable(BasketModel basket) {
		
		Double total = 0.0;
		
		for(ProductModel product : basket.getProducts()) {
			
			for(PromotionModel promotion: product.getPromotions()) {
				
				if(promotion.getId().equals(PromotionTypeEnum.BUY_X_GET_Y_FREE.getCode())) 
					total += promotionService.calculatorBuyXGetYFree(product, promotion);
				else if(promotion.getId().equals(PromotionTypeEnum.QTY_BASED_PRICE_OVERRIDE.getCode())) 
					total += promotionService.calculatorQtdBasedPriceOverride(product, promotion);
				else if(promotion.getId().equals(PromotionTypeEnum.FLAT_PERCENT.getCode()))
					total += promotionService.calculatorFlatPercent(product, promotion);
				
				
			}
			if(product.getPromotions().isEmpty())
				total += calculatePriceWithOutPromotion(product);
		}
		
		return new BasketTotalPayableDTO(total);
	}
	
	private Double calculatePriceWithOutPromotion(ProductModel product) {
		Double priceInDouble = DoubleTools.getValueWithSeparator(product.getPrice()) * product.getAmount();
		return DoubleTools.decimalFormat(DECIMAL_FORMAT, priceInDouble);
	}

	public PriceOrderDetailsDTO getPriceOrder(Integer id) {
		BasketModel basket = findById(id);
		
		Double total = calculateTotal(basket);
		BasketTotalPayableDTO totalPayable = calculateTotalPayable(basket);
		Double totalPromos = calculateTotalPromo(total, totalPayable.getTotalPrice());
		List<String> basketContents = getBasketContents(basket);
		
		return new PriceOrderDetailsDTO(total, totalPayable.getTotalPrice(), totalPromos, basketContents);
	}

	private Double calculateTotalPromo(Double total, Double totalPrice) {
		return total - totalPrice;
	}
}
