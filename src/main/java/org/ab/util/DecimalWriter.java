package org.ab.util;

import java.text.*;

public class DecimalWriter {

	public static String getDecimalSpoken(final String value) {
		
		final Double dkwota = Translator.parseDoubleIfNotNull(value);
		return getValueStatement(dkwota);
	}

	// Tblica opis�w warto�ci jednostek.
	static String[] Units = { "zero", "jeden", "dwa", "trzy", "cztery", "pi��",
			"sze��", "siedem", "osiem", "dziewi��", "dziesi��", "jedena�cie",
			"dwana�cie", "trzyna�cie", "czterna�cie", "pi�tna�cie",
			"szesna�cie", "siedemna�cie", "osiemna�cie", "dziewi�tna�cie" };

	// Tablica opis�w dziesi�tek
	static String[] Tens = { "dwadzie�cia", "trzydzie�ci", "czterdzie�ci",
			"pi��dziesi�t", "sze��dziesi�t", "siedemdziesi�t", "osiemdziesi�t",
			"dziewi��dziesi�t" };

	// Tablica obis�w setek
	static String[] Hundreds = { "", "sto", "dwie�cie", "trzysta", "czterysta",
			"pi��set", "sze��set", "siedemset", "osiemset", "dziewi��set" };

	// Dwuwymiarowa tablica tysi�cy,milion�w,miliar�w ...
	static String[][] OtherUnits = { { "tysi�c", "tysi�ce", "tysi�cy" },
			{ "milion", "miliony", "milion�w" },
			{ "miliard", "miliardy", "miliard�w" } };

	// Konwersja ma�ych liczb ....
	static String SmallValueToWords(int n) {
		if (n == 0) {
			return null;
		}

		StringBuilder valueInWords = new StringBuilder();

		// Konwertuj setki.

		int temp = n / 100;
		if (temp > 0) {
			valueInWords.append(Hundreds[temp]);
			n -= temp * 100;
		}

		// Konwertuj dziesi�tki i jedno�ci.

		if (n > 0) {
			if (valueInWords.length() > 0) {
				valueInWords.append(" ");
			}

			if (n < 20) {
				// Liczby poni�ej 20 przekonwertuj na podstawie
				// tablicy jedno�ci.

				valueInWords.append(Units[n]);
			} else {
				// Wi�ksze liczby przekonwertuj ��cz�c nazwy
				// krotno�ci dziesi�tek z nazwami jedno�ci.
				valueInWords.append(Tens[(n / 10) - 2]);
				int lastDigit = n % 10;

				if (lastDigit > 0) {
					valueInWords.append(" ");
					valueInWords.append(Units[lastDigit]);
				}
			}
		}
		return valueInWords.toString();
	}

	// Obliczenia dla du�ych liczb ... i odmiana prawid�owa ich warto�ci..
	static int GetBigUnitIndex(long n) {
		int lastDigit = (int) n % 10;

		if ((n >= 10 && (n <= 20 || lastDigit == 0)) || (lastDigit > 4)) {
			return 2;
		}
		return (lastDigit == 1) ? 0 : 1;
	}

	static long ToWords(StringBuilder valueInWords, long n, int level) {
		int smallValue = 0;
		// long divisor = (long)Math.pow(10000, (long)level + 1);
		long divisor = (long) Math.pow(1000, (long) level + 1);

		if (divisor <= n) {
			// Je�li liczb� da si� podzieli� przez najbli�sz�
			// pot�g� 1000, kontynuuj rekurencj�.

			n = ToWords(valueInWords, n, level + 1);
			smallValue = (int) (n / divisor);

			if (valueInWords.length() > 0) {
				valueInWords.append(" ");
			}

			if (smallValue > 1) {
				valueInWords.append(SmallValueToWords(smallValue));
				valueInWords.append(" ");
			}
			valueInWords.append(OtherUnits[level][GetBigUnitIndex(smallValue)]);
		}

		return n - smallValue * divisor;
	}

	static String ToWords(long value) {
		if (value == 0) {
			// Zero.
			return Units[0];
		}
		StringBuilder valueInWords = new StringBuilder();
		int smallValue = (int) ToWords(valueInWords, value, 0);
		if (smallValue > 0) {
			if (valueInWords.length() > 0) {
				valueInWords.append(" ");
			}
			valueInWords.append(SmallValueToWords(smallValue));
		}
		return valueInWords.toString();
	}

	static long liczba_zlotych(double kwota) {

		String kwotaString = "" + kwota;
		kwotaString = kwotaString.substring(0, kwotaString.indexOf("."));
		Long dzlote = new Long(kwotaString);
		return (long) dzlote;
	}

	static long liczba_groszy(double grosze) {
		// Tworz� format zmiennych aby uzyska� liczb� w frmie tekstowej
		DecimalFormat dfx = new DecimalFormat("0.00");
		String szlote = dfx.format(grosze);
		// Odcinam grosze
		String bgzlote = (String) szlote.substring(0, szlote.length() - 3);
		Double dzlote = new Double(bgzlote);
		// Od kowty z groszami odejmuj� kwot� bez.
		Long groszy = (long) (grosze * 100 - dzlote * 100);
		return groszy;
	}

	static String getValueStatement(final Double amount) {
		if(amount == null){
			return "";
		} else {	
			final Double abs = Math.abs(amount);
			return minusHandled(ToWords(liczba_zlotych(abs)) + " z�, "
						+ ToWords(liczba_groszy(abs)) + " gr.", amount);
		}
	}

	private static String minusHandled(final String amountStatement, final Double amount) {
		if(amount.compareTo(0.0) < 0){
			return "minus ".concat(amountStatement);
		} else {
			return amountStatement;
		}
	}
}