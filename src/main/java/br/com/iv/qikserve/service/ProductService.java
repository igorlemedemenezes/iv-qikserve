package br.com.iv.qikserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.model.ProductModel;
import br.com.iv.qikserve.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public ProductModel save(ProductModel p) {
		return repo.save(p);
	}
	
	@Autowired
	private WiremockClient wiremockClient;
	
	public List<ProductModel> getProducts() {
		return wiremockClient.getProducts();
	}

	public ProductModel getProduct(String id) {
		return wiremockClient.getProductId(id);
	}
	
}
