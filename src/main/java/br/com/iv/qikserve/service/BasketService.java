package br.com.iv.qikserve.service;

import org.springframework.stereotype.Service;

import br.com.iv.qikserve.model.BasketModel;

@Service
public class BasketService {

//	When add item, need check how much have at the itens.
	
//	@Autowired
//	private  
	
	public BasketModel create(BasketModel basket) {
//		return repo.save(basket);
//		CHECK IF EXIST THE ID IN MOCK-API.
		return basket;
	}
	
	
	
}
