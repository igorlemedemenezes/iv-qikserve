package br.com.iv.qikserve.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "promotion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromotionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPromotion;
	
	private String id;
	private String type;
	private Integer required_qty;
	private Integer price;
	private Integer amount;
	private Integer free_qty;
	
}
