package br.com.iv.qikserve.dto;

import br.com.iv.qikserve.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

	private String id;
	private String name;
	private Integer productQty;

	public ProductDTO(ProductModel e) {
		this.id = e.getId();
		this.name = e.getName();
		this.productQty = e.getProductQty();
	}
	
}
