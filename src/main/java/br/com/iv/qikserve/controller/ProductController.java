package br.com.iv.qikserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iv.qikserve.dto.ProductDTO;
import br.com.iv.qikserve.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	private ResponseEntity<List<ProductDTO>> getProducts(){
		return ResponseEntity.ok().body(productService.getProducts());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String id){
		return ResponseEntity.ok().body(productService.getProduct(id));
	}
	
}
