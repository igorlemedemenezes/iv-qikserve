package br.com.iv.qikserve.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.iv.qikserve.model.BasketModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BasketDTO {

	private Integer userId;
	private List<ProductDTO> products = new ArrayList<>();

	public BasketDTO(BasketModel basket) {
		this.userId = basket.getClient().getId();
		this.products = basket.getProducts().stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	
}
