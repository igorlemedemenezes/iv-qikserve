package br.com.iv.qikserve.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PriceOrderDetailsDTO {

	public Double total;
	public Double totalPayable;
	public Double totalPromos;
	public List<String> basketContents;
	
}
