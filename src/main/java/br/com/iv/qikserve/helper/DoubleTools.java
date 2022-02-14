package br.com.iv.qikserve.helper;

import java.math.BigDecimal;

public class DoubleTools {

	public static Double getValueWithSeparator(Integer value) {
		BigDecimal valueBigDecimal = new BigDecimal(value);
		valueBigDecimal = valueBigDecimal.divide(new BigDecimal(100));
		return valueBigDecimal.doubleValue();
	}
		
	
}
