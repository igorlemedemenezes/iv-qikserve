package br.com.iv.qikserve.api.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.iv.qikserve.dto.ProductDTO;

@FeignClient(value = "wiremock", url = "http://localhost:8081/")
public interface WiremockClient {
	
	@GetMapping(value = "/products")
	List<ProductDTO> getProducts(); 
	
	@GetMapping(value = "/products/{id}")
	ProductDTO getProductId(@PathVariable("id") String id);
	
}
