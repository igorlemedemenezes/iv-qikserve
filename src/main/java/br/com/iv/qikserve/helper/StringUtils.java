package br.com.iv.qikserve.helper;

import java.util.Currency;
import java.util.Locale;

public class StringUtils {

	public static String getValueWithSymbolFromUK(Double value) {
		Locale locale = Locale.UK;
		Currency curr = Currency.getInstance(locale);
		return curr.getSymbol() + " " + value;
	}
	
}
