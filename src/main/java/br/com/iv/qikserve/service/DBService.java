package br.com.iv.qikserve.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.model.ClientModel;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.model.PromotionModel;
import br.com.iv.qikserve.repository.BasketRepository;
import br.com.iv.qikserve.repository.ClientRepository;


//This class is used to generate local data accelerate the development.
@Service
public class DBService {

	@Autowired
	private ClientRepository cliRepo;

	@Autowired
	private BasketRepository basketRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() {
		
		PromotionModel promotion = new PromotionModel(null, "Gm1piPn7Fg", "FLAT_PERCENT", null, null, 10, null);
		
		ProductModel product = new ProductModel(null, "C8GDyLrHJb", "Amazing Salad!", 499, 4, null, Arrays.asList(promotion));		
		
		ClientModel cli1 = new ClientModel(null, "dev", pe.encode("dev"), null);
		cliRepo.save(cli1);
		
		BasketModel basket = new BasketModel(null, cli1, Arrays.asList(product));
		basketRepo.save(basket);
		
	}
	
	
}
