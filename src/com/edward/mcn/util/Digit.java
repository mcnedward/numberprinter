package com.edward.mcn.util;

public class Digit {
	public static String[] BASE_UNITS = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	public static String[] TENS_UNITS = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	public static String[] ROMAN_ONES = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
	public static String[] ROMAND_TENS = { "", "X", "XX", "XXX", "XL", "L", " LX", "LXX", "LXXX", "XC" };
	public static String[] ROMAN_HUNDREDS = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	public static String[] ROMAN_THOUSANDS = { "", "M", "MM", "MMM" };
	private int number;
	private int one, ten, hundred, thousand;
	private String englishOne = "", englishTen = "", englishHundred = "", englishThousand = "";
	private String romanOne = "", romanTen = "", romanHundred = "", romanThousand = "";
	private String englishWord;
	private String romanNumeral;

	public Digit() {
	}

	public Digit(int number) {
		this.number = number;
		if (number < 1) {
			this.englishWord = (this.romanNumeral = "THAT NUMBER IS OUT OF BOUNDS!");
			return;
		}
		if (number > 3999) {
			this.englishWord = (this.romanNumeral = "THAT NUMBER IS OUT OF BOUNDS!");
			return;
		}
		findDigitPositions(number);
		convertToEnglish();
		convertToRomanNumeral();
	}

	private void findDigitPositions(int number) {
		int length = String.valueOf(number).length();

		int position = (int) Math.pow(10.0D, length - 1);
		for (int x = 0; x < length; x++) {
			int digitToAdd = number / position;
			addNumber(digitToAdd, position);
			number %= position;
			position /= 10;
		}
	}

	public void addNumber(int number, int position) {
		switch (position) {
		case 1:
			this.one = number;
			this.englishOne = BASE_UNITS[number];
			this.romanOne = ROMAN_ONES[number];
			break;
		case 10:
			this.ten = number;
			this.englishTen = TENS_UNITS[number];
			this.romanTen = ROMAND_TENS[number];
			break;
		case 100:
			this.hundred = number;
			this.englishHundred = BASE_UNITS[number];
			this.romanHundred = ROMAN_HUNDREDS[number];
			break;
		case 1000:
			this.thousand = number;
			this.englishThousand = BASE_UNITS[number];
			this.romanThousand = ROMAN_THOUSANDS[number];
		}
	}

	private void convertToEnglish() {
		String word = "";
		if (ten == 1) {
			word = BASE_UNITS[(one + 10)];
		} else if (ten == 0) {
			word = englishOne;
		} else {
			word = one == 0 ? englishTen : englishTen + "-" + englishOne;
		}
		if (hundred != 0) {
			word = englishHundred + " hundred " + word;
		}
		if (thousand != 0) {
			word = word == "" ? englishThousand + " thousand" : englishThousand + " thousand, " + word;
		}
		// if (this.ten == 1) {
		// this.englishTen = BASE_UNITS[(this.one + 10)];
		// this.englishWord += this.englishTen;
		// } else if (this.ten == 0) {
		// this.englishWord += this.englishOne;
		// } else {
		// this.englishWord += (this.one == 0 ? this.englishTen : new StringBuilder(String.valueOf(this.englishTen)).append("-")
		// .append(this.englishOne).toString());
		// }
		englishWord = word.trim();
		this.englishWord.trim();
	}

	private void convertToRomanNumeral() {
		this.romanNumeral = (this.romanThousand + this.romanHundred + this.romanTen + this.romanOne).trim();
	}

	public String toRomanNumeral() {
		return this.romanNumeral;
	}

	public String toString() {
		return this.englishWord.toUpperCase();
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
