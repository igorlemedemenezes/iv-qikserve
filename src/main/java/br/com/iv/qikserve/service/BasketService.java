package br.com.iv.qikserve.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.dto.BasketDTO;
import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ProductModel;

@Service
public class BasketService {

//	When add item, need check how much have at the itens.
	
	@Autowired
	private WiremockClient wiremockClient;
	
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

	public void checkoutBasket(Integer id) {
		BasketModel basket = findById(id);
//		basket.getProducts().stream().
	}
	
	public Double getTotalPrice(BasketModel basket) {
		return null;
	}
	
	public Double calculateTotal(BasketModel basket) {
//		basket.getProducts().stream().
		return null;
	}
	
}
