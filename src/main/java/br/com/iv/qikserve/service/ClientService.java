package br.com.iv.qikserve.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ClientModel;

@Service
public class ClientService {

	public BasketModel getClientLastBasket(ClientModel cli) {
		long count = cli.getBaskets().stream().count();
		Stream<BasketModel> stream = cli.getBaskets().stream();
		  
		return stream.skip(count - 1).findFirst().get();
		
	}
	
	
}
