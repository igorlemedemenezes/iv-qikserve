package br.com.iv.qikserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.api.feignclient.WiremockClient;
import br.com.iv.qikserve.dto.ProductDTO;

@Service
public class ProductService {

	@Autowired
	private WiremockClient wiremockClient;
	
	public List<ProductDTO> getProducts() {
		return wiremockClient.getProducts();
	}

	public ProductDTO getProduct(String id) {
		return wiremockClient.getProductId(id);
	}
	
}
