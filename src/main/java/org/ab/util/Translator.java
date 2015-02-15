package org.ab.util;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import com.google.common.base.Joiner;

public class Translator {

	private static String commaNumber(final String numericString) {
		return numericString.replaceAll(",", ".");
	}
	public static String complement(final String word, final char c, final int resultLenght) {

		final StringBuilder sb = new StringBuilder(word != null ? word : "");
		final int wordLenght = sb.length();

		for(int i = resultLenght; i >= wordLenght; i-- ){
			sb.append(c);
		}
		return sb.toString();
	}
	public static String emptyAsNull(final String parameter) {

		return parameter == null || parameter.isEmpty() ? null : parameter;
	}

	public static boolean getBoolean(final String parameter) {
		return StringUtils.isNotEmpty(parameter) && TRUE.equals(parameter);
	}

	public static String getCalendarMonthName(final Calendar calendarDayFrom) {
		final String result;
		switch (calendarDayFrom.get(Calendar.MONTH)) {
		case 0:
			result = "Styczeñ";
			break;
		case 1:
			result = "Luty";
			break;
		case 2:
			result = "Marzec";
			break;
		case 3:
			result = "Kwiecieñ";
			break;
		case 4:
			result = "Maj";
			break;
		case 5:
			result = "Czerwiec";
			break;
		case 6:
			result = "Lipiec";
			break;
		case 7:
			result = "Sierpieñ";
			break;
		case 8:
			result = "Wrzesieñ";
			break;
		case 9:
			result = "PaŸdziernik";
			break;
		case 10:
			result = "Listopad";
			break;
		case 11:
			result = "Grudzieñ";
			break;
		default:
			result = "";

		}
		return result;
	}

	public static String join(final String... parts) {
		return Joiner.on(" ").skipNulls().join(parts);
	}

	public static String parseDecimalStr(final String source){
		final String result;
		final Double doubleValue = parseDoubleIfNotNull(source);
		if(doubleValue == null){
			result = null;
		} else {
			result = doubleValue.toString();
		}
		return result;
	}

	private static Double parseDouble(final String source) {
		try{
			return new BigDecimal(source).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (final NumberFormatException e) {
			return null;
		}
	}

	public static Double parseDoubleIfNotNull(final String source){
		final Double result;
		if(StringUtils.isNotEmpty(source)){
			result = parseDouble(source.replace(',', '.'));
		} else {
			result = null;
		}
		return result;
	}

	public static int parseInt(final String value) {
		int result = 0;
		if(value != null){
			result = parseIntNotNull(value);
		}
		return result;
	}

	public static String parseIntegerStr(final String source){
		final String result;
		final Double doubleValue = parseDoubleIfNotNull(source);
		if(doubleValue == null){
			result = null;
		} else {
			result = "" + doubleValue.intValue();
		}
		return result;
	}

	private static int parseIntNotNull(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (final NumberFormatException e) {
			return -1;
		}
	}

	public static BigDecimal toAmount(final String value) {
		return toDecimal(value, 2);
	}

	public static BigDecimal toDecimal(final String value, final int scale) {
		BigDecimal decimal = BigDecimal.ZERO;
		if(StringUtils.isNotBlank(value)){
			try {
				decimal = new BigDecimal(commaNumber(value));
			} catch (final Exception e) {
				log.warn("Cannot convert to decimal value: " + value);
			}
		}
		return decimal.setScale(scale);
	}

	public static LocalDate toLocalDate(final String dateStr){
		if(StringUtils.isBlank(dateStr)){
			return null;
		} else{
			return LocalDate.parse(dateStr);
		}
	}

	private static Logger log = Logger.getLogger(Translator.class);

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private static final String TRUE = "true";

	private Translator(){}
}
