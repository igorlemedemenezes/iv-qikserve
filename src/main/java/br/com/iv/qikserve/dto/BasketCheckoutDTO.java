package br.com.iv.qikserve.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasketCheckoutDTO {

	private Double totalPrice;
	List<String> basketContents;
	
}
