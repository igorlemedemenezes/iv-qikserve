package br.com.iv.qikserve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PromotionTypeEnum {

	QTY_BASED_PRICE_OVERRIDE("QTY_BASED_PRICE_OVERRIDE"),
	BUY_X_GET_Y_FREE("BUY_X_GET_Y_FREE"),
	FLAT_PERCENT("FLAT_PERCENT");
	
	private String type;
	
}
