package br.com.iv.qikserve.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PriceOrderDetailsDTO {

	public String total;
	public String totalPayable;
	public String totalPromos;
	public List<String> basketContents;
	
}
