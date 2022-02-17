package br.com.iv.qikserve.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;
	
	private String id;
	private String name;
	private Integer price;
	private Integer productQty;
	

	@ManyToOne
	@JoinColumn(name = "basket_id")
	private BasketModel basket;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PromotionModel> promotions = new ArrayList<>();
	
}
