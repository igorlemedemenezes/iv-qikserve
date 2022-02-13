package br.com.iv.qikserve.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.service.BasketService;

@RestController
@RequestMapping(value = "/basket")
public class BasketController {

	@Autowired
	private BasketService service;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<Void> addItemToTheBasket(@PathVariable Integer id){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<BasketModel> createBasket(@RequestBody @NotNull BasketModel basketModel){
		BasketModel basket = service.create(basketModel);
		return ResponseEntity.created(null).body(basket);
	}
	
}
