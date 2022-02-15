package br.com.iv.qikserve.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleTools {

	public static Double getValueWithSeparator(Integer value) {
		BigDecimal valueBigDecimal = new BigDecimal(value);
		valueBigDecimal = valueBigDecimal.divide(new BigDecimal(100));
		return valueBigDecimal.doubleValue();
	}
		
	
	public static Double decimalFormat(String format, Double value) {
		DecimalFormat df = new DecimalFormat(format);
		return Double.parseDouble(df.format(value).replace(",", "."));
	}
	
	
}
