package br.com.iv.qikserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.UserClient;
import br.com.iv.qikserve.model.ProductModel;

@Service
public class ProductService {

	@Autowired
	private UserClient userClient;
	
	public List<ProductModel> getProducts() {
		return userClient.getProducts();
	}

	public ProductModel getProduct(String id) {
		return userClient.getProductId(id);
	}
	
}
