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
import br.com.iv.qikserve.exceptions.ObjectNotFoundException;
import br.com.iv.qikserve.helper.DoubleTools;
import br.com.iv.qikserve.helper.StringUtils;
import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;
import br.com.iv.qikserve.repository.BasketRepository;

@Service
public class BasketService {
	
	private final String DECIMAL_FORMAT = "##0.00"; 
	
//	When add item, need check how much have at the itens.
	
	@Autowired
	private WiremockClient wiremockClient;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BasketRepository repo;
	
	public BasketModel save(BasketModel basket) {
		return repo.save(basket);
	}

	public BasketModel create(BasketModel basket) {
		
		List<ProductModel> products = basket.getProducts().stream().map(e -> {
			ProductModel product = wiremockClient.getProductId(e.getId());
			product.setProductQty(e.getProductQty());
			product.setBasket(basket);
			return product;
		}).collect(Collectors.toList());
		
		basket.setProducts(products);
		
		return repo.save(basket);
	}
	

	public BasketModel addItem(BasketDTO basketDTO) {
		BasketModel basket = findById(basketDTO.getBasketId());
		ProductModel product = wiremockClient.getProductId(basketDTO.getProduct().getId());
		addNewProductToBasket(basketDTO, basket, product);
		return basket;
	}

	private BasketModel addNewProductToBasket(BasketDTO basketDTO, BasketModel basket, ProductModel productRetrieved) {
		
		for(ProductModel product : basket.getProducts()) {
			if(product.getId().equals(basketDTO.getProduct().getId())){
				Integer totalAmount = product.getProductQty()+basketDTO.getProduct().getAmount();
				product.setProductQty(totalAmount);
				productService.save(product);
				return basket;
			}
		}
		
		productRetrieved.setProductQty(basketDTO.getProduct().getAmount());
		productRetrieved.setBasket(basket);
		basket.getProducts().add(productRetrieved);
		return save(basket);
		
	}

	public BasketTotalPayableDTO checkoutBasketWithTotalPayable(Integer id) {
		BasketModel basket = findById(id);
		return calculateTotalPayable(basket);
	}
	
	public List<String> getBasketContents(BasketModel basket){
		
		List<String> basketContents = new ArrayList<String>();
		
		basket.getProducts().forEach(e -> {
			basketContents.add(e.getProductQty() + "x " + e.getName());
		});
		
		return basketContents;

	}
	
	public Double calculateTotal(BasketModel basket) {
		
		return  DoubleTools.decimalFormat(DECIMAL_FORMAT, 
				basket.getProducts()
					.stream()
					.mapToDouble(product -> (product.getProductQty() * DoubleTools.getValueWithSeparator(product.getPrice())))
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
		
		return new BasketTotalPayableDTO(DoubleTools.decimalFormat(DECIMAL_FORMAT, total));
	}
	
	private Double calculatePriceWithOutPromotion(ProductModel product) {
		Double priceInDouble = DoubleTools.getValueWithSeparator(product.getPrice()) * product.getProductQty();
		return DoubleTools.decimalFormat(DECIMAL_FORMAT, priceInDouble);
	}

	public PriceOrderDetailsDTO getPriceOrder(Integer id) {
		BasketModel b = findById(id);
		
		Double total = calculateTotal(b);
		BasketTotalPayableDTO totalPayable = calculateTotalPayable(b);
		Double totalPromos = calculateTotalPromo(total, totalPayable.getTotalPrice());
		List<String> basketContents = getBasketContents(b);
		
		return new PriceOrderDetailsDTO(
				StringUtils.getValueWithSymbolFromUK(total), 
				StringUtils.getValueWithSymbolFromUK(totalPayable.getTotalPrice()), 
				StringUtils.getValueWithSymbolFromUK(totalPromos), 
				basketContents);
	}

	private Double calculateTotalPromo(Double total, Double totalPrice) {
		return DoubleTools.decimalFormat(DECIMAL_FORMAT, total - totalPrice);
	}
	
	private BasketModel findById(Integer id) {
		Optional<BasketModel> basket = repo.findById(id);
		
		if(basket.isPresent()) 
			return basket.get();
		
        throw new ObjectNotFoundException();
	}
	
}
