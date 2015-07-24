package org.ab.util;

import java.util.HashMap;
import java.util.Map;

public class IdnTranslator {

	static Map<Character, Character> polishLettersMap;
	static {
		polishLettersMap = new HashMap<Character, Character>();
		polishLettersMap.put(' ', '_');
		polishLettersMap.put('�', 'a');
		polishLettersMap.put('�', 'a');
		polishLettersMap.put('�', 'o');
		polishLettersMap.put('�', 's');
		polishLettersMap.put('�', 'l');
		polishLettersMap.put('�', 'z');
		polishLettersMap.put('�', 'z');
		polishLettersMap.put('�', 'c');
		polishLettersMap.put('�', 'n');

	}

	public String convertNameToIdn(final String value) {
		final String nameLowerCase = value.toLowerCase().replaceAll("\\s", "");
		return substitutePolishLettersWithNormal(nameLowerCase);
	}

	private  boolean isNormalLetter(final int chVal) {
		return chVal >= 65 && chVal <= 90 || chVal >= 97 && chVal <= 122;
	}

	private  String substitutePolishLettersWithNormal(final String org) {
		final StringBuilder sb = new StringBuilder();
		for (final char ch: org.toCharArray()) {
			if (Character.isLetter(ch)) {
				sb.append(isNormalLetter(ch)? ch : polishLettersMap.get(ch));
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

}
