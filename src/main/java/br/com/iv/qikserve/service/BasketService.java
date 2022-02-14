package br.com.iv.qikserve.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.model.BasketModel;

@Service
public class BasketService {

//	When add item, need check how much have at the itens.
	
	@Autowired
	private WiremockClient wiremockClient;
	
	public BasketModel create(BasketModel basket) {
		System.out.println(basket.toString());
		basket.getProducts().stream().map(product -> {
			return wiremockClient.getProductId(product.getProductId());
		}).collect(Collectors.toList());
		return basket;
	}
	
	
	
}
