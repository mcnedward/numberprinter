package com.edward.mcn.util;

public class Conversion {
	public static String convertToEnglish(int number) {
		Digit digit = new Digit(number);
		return digit.toString();
	}

	public static String convertToRomanNumeral(int number) {
		Digit digit = new Digit(number);
		return digit.toRomanNumeral();
	}
}
