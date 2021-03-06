package br.com.iv.qikserve.controller;

import java.net.URI;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.iv.qikserve.dto.AddItemDTO;
import br.com.iv.qikserve.dto.BasketDTO;
import br.com.iv.qikserve.dto.BasketFormDTO;
import br.com.iv.qikserve.dto.BasketTotalPayableDTO;
import br.com.iv.qikserve.dto.ItemDTO;
import br.com.iv.qikserve.dto.PriceOrderDetailsDTO;
import br.com.iv.qikserve.model.BasketModel;
import br.com.iv.qikserve.service.BasketService;

@RestController
@RequestMapping(value = "/basket")
public class BasketController {

	@Autowired
	private BasketService service;
	
	@PostMapping
	private ResponseEntity<Void> createBasket(@RequestBody @NotNull BasketModel basketModel){
		BasketModel basket = service.create(basketModel);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(basket.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/add")
	private ResponseEntity<AddItemDTO> addItemToTheBasket(@RequestBody @NotNull BasketFormDTO basketDTO){
		AddItemDTO item = service.addItem(basketDTO);
		return ResponseEntity.ok().body(item);
	}

	@GetMapping("/itens/{id}")
	private ResponseEntity<ItemDTO> getAllItens(@PathVariable("id") Integer id){
		ItemDTO itens = service.getAllItens(id);
		return ResponseEntity.ok().body(itens);
	}
	
	@GetMapping("/id")
	private ResponseEntity<BasketDTO> getBasket(@PathVariable("id") Integer id){
		BasketDTO basket = service.getLastBasketCreateByUser(id);
		return ResponseEntity.ok().body(basket);
	}
	
	@GetMapping(value = "/checkout/{id}")
	private ResponseEntity<BasketTotalPayableDTO> checkoutBasket(@PathVariable("id") Integer id){
		BasketTotalPayableDTO totalPayable = service.checkoutBasketWithTotalPayable(id);
		return ResponseEntity.ok().body(totalPayable);
	}
	
	@GetMapping(value = "/order-details/{id}")
	private ResponseEntity<PriceOrderDetailsDTO> priceOrderDetails(@PathVariable("id") Integer id){
		PriceOrderDetailsDTO details = service.getPriceOrder(id);
		return ResponseEntity.ok().body(details);
	}
	
}
