package br.com.iv.qikserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddItemDTO {
	private String message;
	
	public AddItemDTO() {
		this.message = "Item added successfully to the basket.";
	}
	
}
