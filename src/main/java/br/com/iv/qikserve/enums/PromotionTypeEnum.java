package br.com.iv.qikserve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PromotionTypeEnum {

	QTY_BASED_PRICE_OVERRIDE("Dwt5F7KAhi"),
	BUY_X_GET_Y_FREE("ZRAwbsO2qM"),
	FLAT_PERCENT("Gm1piPn7Fg");
	
	private String code;
	
}
