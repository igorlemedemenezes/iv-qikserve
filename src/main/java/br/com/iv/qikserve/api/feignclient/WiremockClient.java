package br.com.iv.qikserve.api.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.iv.qikserve.model.ProductModel;

@FeignClient(value = "wiremock", url = "http://localhost:8081/")
public interface WiremockClient {
	
	@GetMapping(value = "/products")
	List<ProductModel> getProducts(); 
	
	@GetMapping(value = "/products/{id}")
	ProductModel getProductId(@PathVariable("id") String id);
	
}
